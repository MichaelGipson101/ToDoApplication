package com.example.todoapplication

/**
 * This todo data class has three fields that map to the columns in the todo table
 * in the db. it will be used to exchange data between the databse and the recyclerview.
 */
data class ToDo (
        //declarea mutable int to store in the todo id
        var id: Int,
        //declare an immutable string to store the todo name
        val name: String,
        //declare a mutable boolean to store the todo is_checked
        var isChecked: Boolean = false

        )