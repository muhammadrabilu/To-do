package com.rabilu.todo.data.repositories

import androidx.lifecycle.asLiveData
import com.rabilu.todo.data.remote.Resources
import com.rabilu.todo.data.local.DataStoreManager
import com.rabilu.todo.data.local.db.TodoDB
import com.rabilu.todo.data.local.model.Todo
import com.rabilu.todo.data.remote.Services
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import org.json.JSONObject
import javax.inject.Inject

class TodoRepository @Inject constructor(
    private val dataStoreManager: DataStoreManager, private val services: Services, db: TodoDB
) {
    private val todoDao = db.todoDao()
    private val _addTodoResponse = MutableStateFlow<Resources<String>>(Resources.Loading())
    val addTodoResponse: Flow<Resources<String>> = _addTodoResponse

    val todos = db.todoDao().getAllTodos()

    suspend fun addTodo(todo: Todo) {
        _addTodoResponse.value = Resources.Loading(isloading = true)
        try {
            val response = services.addTodo(todo)

            if (response.isSuccessful) {
                todoDao.insert(Todo(todo = todo.todo))
                _addTodoResponse.value = Resources.Success(data = "Todo added")
            } else {
                val error = JSONObject(response.errorBody()?.string().toString())
                _addTodoResponse.value = Resources.Error(errorMessage = error.getString("message"))
            }
        } catch (e: Exception) {
            _addTodoResponse.value = Resources.Error(errorMessage = e.localizedMessage!!.toString())
        }
    }

    suspend fun update(todo: Todo) {
        todoDao.insert(todo)
        _addTodoResponse.value = Resources.Success(data = "Updated")
    }

    suspend fun deleteTodo(todo: Todo) {
        todoDao.deleteTodo(todo)
    }

    suspend fun complete(todo: Todo) {
        todoDao.completTodo(todo)
    }

}