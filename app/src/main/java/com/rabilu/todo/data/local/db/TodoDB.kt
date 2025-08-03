package com.rabilu.todo.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rabilu.todo.data.local.model.Todo

@Database(
    entities = [Todo::class],
    version = 1
)
abstract class TodoDB : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}
