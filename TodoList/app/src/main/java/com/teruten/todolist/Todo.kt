package com.teruten.todolist

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "todo")
data class Todo(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var title: String = "",
    var date: Long = 0
)