package com.teruten.todolist

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_edit.*
import java.util.*

class EditActivity : AppCompatActivity() {

    private val calendar = Calendar.getInstance()
    private lateinit var todoViewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        todoViewModel = ViewModelProviders.of(this).get(TodoViewModel::class.java)
        val id = intent.getLongExtra("id", -1L)
        if(id == -1L) {
            insertMode()
        } else {
            updateMode(id)
        }

        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        }
    }

    @SuppressLint("RestrictedApi")
    private fun insertMode() {
        deleteFab.visibility = View.GONE
        doneFab.setOnClickListener {
            insertTodo()
        }
    }

    private fun updateMode(id: Long) {
        todoViewModel.getTodoById(id).observe(this, android.arch.lifecycle.Observer { todo ->
            todo?.let {
                todoEditText.setText(todo.title)
                calendarView.date = todo.date
            }
        })

        doneFab.setOnClickListener {
            updateTodo(id)
        }

        deleteFab.setOnClickListener {
            deleteTodo(id)
        }
    }

    private fun insertTodo() {
        val todo = Todo(0,
            todoEditText.text.toString(),
            calendar.timeInMillis
        )
        todoViewModel.insert(todo) { finish() }
    }

    private fun updateTodo(id: Long) {
        val todo = Todo(id,
            todoEditText.text.toString(),
            calendar.timeInMillis
        )
        todoViewModel.insert(todo) { finish() }
    }

    private fun deleteTodo(id: Long) {
        todoViewModel.delete(id) { finish() }
    }
}
