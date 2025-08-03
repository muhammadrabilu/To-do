package com.rabilu.todo.ui.presentation.todo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rabilu.todo.data.local.model.Todo
import com.rabilu.todo.data.repositories.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(private val todoRepository: TodoRepository) : ViewModel() {

    val todoAddResponse = todoRepository.addTodoResponse
    val allTodos = todoRepository.todos

    fun addTodo(todo: Todo) {
        viewModelScope.launch {
            todoRepository.addTodo(todo)
        }
    }

    fun updateTodo(todo: Todo) {
        viewModelScope.launch {
            todoRepository.update(todo)
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            todoRepository.deleteTodo(todo)
        }
    }

    fun completeTodo(todo: Todo) {
        viewModelScope.launch {
            todoRepository.complete(todo)
        }
    }

}