package com.rabilu.todo.data.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.rabilu.todo.data.local.model.Todo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: Todo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(todo: List<Todo>)

    @Delete
    suspend fun deleteTodo(todo: Todo)

    @Query("select * from todo")
    fun getAllTodos(): Flow<List<Todo>>

    @Update
    suspend fun completTodo(todo: Todo)

}