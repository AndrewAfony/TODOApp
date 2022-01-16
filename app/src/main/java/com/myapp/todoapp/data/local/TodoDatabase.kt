package com.myapp.todoapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.myapp.todoapp.domain.model.Todo

@Database(
    entities = [Todo::class],
    version = 1
)
abstract class TodoDatabase: RoomDatabase() {

    abstract val dao: TodoDao
}