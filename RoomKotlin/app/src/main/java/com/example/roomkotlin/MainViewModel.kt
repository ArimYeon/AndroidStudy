package com.example.roomkotlin

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.room.Room

class MainViewModel(application: Application): AndroidViewModel(application) {
    private val db = Room.databaseBuilder(application, AppDatabase::class.java, "todo-db")
        .build()

    fun getAll(): LiveData<List<Todo>>{
        return db.todoDao().getAll()
    }

    // suspend - 이 함수가 코루틴 안에서 실행되어야 한다는 것을 컴파일러에게 알려줌
    suspend fun insert(todo: Todo) = db.todoDao().insert(todo)
}