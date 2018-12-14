package com.teruten.todolist

import android.app.Application
import android.arch.lifecycle.LiveData
import io.reactivex.Observable

class TodoRepository(application: Application) {

    private val todoDao: TodoDao by lazy {
        val db = TodoRoomDatabase.getInstance(application)!!
        db.todoDao()
    }
    private val todos: LiveData<List<Todo>> by lazy {
        todoDao.getAllTodos()
    }

    fun getAllTodos(): LiveData<List<Todo>> {
        return todos
    }

    fun getTodoById(id: Long): LiveData<Todo> {
        return todoDao.getTodoById(id)
    }

    fun insert(todo: Todo): Observable<Unit> {
        return Observable.fromCallable { todoDao.insert(todo) }
    }

    fun delete(id: Long): Observable<Unit> {
        return Observable.fromCallable { todoDao.deleteById(id) }
    }
}