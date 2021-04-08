package com.example.todoapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * This function gets called when the add button is clicked. It adds a todo into the database.
     * @param view Mainactivity view
     */
    fun addTodo(view: View?) {

    }
    /**
     * This function gets called when the delete button is clicked. It deletes a todo into the database.
     * @param view Mainactivity view
     */
    fun deleteTodo(view: View?) {

    }
}