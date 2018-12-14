package com.teruten.todolist

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [Todo::class], version = 1)
abstract class TodoRoomDatabase: RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object {
        private var INSTANCE: TodoRoomDatabase? = null

        fun getInstance(context: Context): TodoRoomDatabase? {
            return INSTANCE ?: synchronized(TodoRoomDatabase::class) {
                INSTANCE ?: Room.databaseBuilder(context.applicationContext,
                    TodoRoomDatabase::class.java, "todo_database").build().also {
                    INSTANCE = it
                }
            }
        }

        fun destoryInstance() {
            INSTANCE = null
        }
    }
}