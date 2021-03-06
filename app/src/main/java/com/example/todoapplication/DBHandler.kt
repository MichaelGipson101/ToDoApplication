package com.example.todoapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHandler(context: Context?, cursorFactory: SQLiteDatabase.CursorFactory?) :
SQLiteOpenHelper(context, DATABASE_NAME, cursorFactory, DATABASE_VERSION){
    /**
     * Creates database table
     * @param db = TODO database
     */
    override fun onCreate(db: SQLiteDatabase?) {
        //define create statement for the todo table
        val query = "CREATE TABLE " + TABLE_TODO_LIST + "(" +
                COLUMN_TODO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TODO_ISCHECKED + " TEXT, " +
                COLUMN_TODO_NAME + " TEXT);"

        //execute create statement
        db?.execSQL(query)
    }

    /**
     * Creates new version of db
     * @param db reference to todoapp databaase
     * @param oldVersion old version of database
     * @param newVersion new version of database
     */
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //define drop statement for the todo table
        val query = "DROP TABLE IF EXISTS " + TABLE_TODO_LIST

        //execute drop statemement
        db?.execSQL(query)

        //call menthod that creates table
        onCreate(db)
    }

    /**
     * This method gets called when the add button of the mainactivity is clicked
     * it inserts a new row into the todo table
     * @param todoName todo name
     */
    fun addTodo(todoName: String?) {
        //get reference to todoapp database
        val db = writableDatabase

        //initialize a contentvalues object
        val values = ContentValues()

        //put data into the contentvalues obj
        values.put(COLUMN_TODO_NAME, todoName)
        values.put(COLUMN_TODO_ISCHECKED, "false")

        //insert data into contentvalues obj into todo table
        db.insert(TABLE_TODO_LIST,null, values)

        //close db connection
        db.close()
    }

    /**
     * This method gets called when the delete button in the main
     * activity gets clicked, it deletes a todo from the DB
     */
    fun deleteTodo(id: Int) {
        //get a reference to the todo db
        val db = writableDatabase

        //define delete statement
        val query = "DELETE FROM " + TABLE_TODO_LIST +
                " WHERE " + COLUMN_TODO_ID + " = " + id

        //execute delete statement
        db.execSQL(query)

        //close db ref
        db.close()
    }

    /**
     * This method gets called when the mainactivity is created
     * and when the add and delete buttons get clicked.
     * @return mutablelist o todos that contains all the data
     * in the todo table
     */
    val todos: MutableList<ToDo>
        get() {
            //get a reference to the todoapp db
            val db = writableDatabase

            //define select statememnt
            val query = "SELECT * FROM " + TABLE_TODO_LIST

            //exec the select statememnt and store its return in an
            //immutable cursor
            val c = db.rawQuery(query, null)

            //create mutablelist of todos that will be returned
            val list: MutableList<ToDo> = ArrayList()

            //loop through the rows in the cursor
            while (c.moveToNext()) {
                //create an immutable todo using the data in the
                //current row in thecursor
                val toDo: ToDo = ToDo(c.getInt(c.getColumnIndex("_id")),
                c.getString(c.getColumnIndex("name")),
                c.getString(c.getColumnIndex("is_checked")).toBoolean());

                //add the todo list that was just created to the mutable list of
                //todos
                list.add(toDo)
            }
            //close db ref
            db.close()

            //return the mutable list of todos
            return list
        }
    companion object {
        //initialize constants for the DB name and version
        private const val DATABASE_NAME = "todoapp.db"
        private const val DATABASE_VERSION = 1

        //initializae constants for the todo table
        private const val TABLE_TODO_LIST = "todo"
        private const val COLUMN_TODO_ID = "_id"
        private const val COLUMN_TODO_NAME = "name"
        private const val COLUMN_TODO_ISCHECKED = "is_checked"
    }
}