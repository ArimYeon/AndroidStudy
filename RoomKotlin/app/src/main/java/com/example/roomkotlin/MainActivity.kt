package com.example.roomkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Room.databaseBuilder(this, AppDatabase::class.java, "todo-db")
                .allowMainThreadQueries().build()

        // LiveData
        db.todoDao().getAll().observe(this, Observer {
            result_text.text = it.toString()
        })

        add_button.setOnClickListener {
            db.todoDao().insert(Todo(todo_edit.text.toString()))
        }
    }
}