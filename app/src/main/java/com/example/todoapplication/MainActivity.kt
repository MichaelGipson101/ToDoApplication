package com.example.todoapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    //declare edittext as a mutable field using null safety
    var toDoEditText: EditText? = null

    //declare recyclerview as amutable field using null safety
    var toDoRecyclerView: RecyclerView? = null

    //declare DBhandler as mutable field using null safety
    var dbHandler: DBHandler? = null

    //declare a todoadapter as a mutable field
    //specify that it will be initialized later
    lateinit var toDoAdapter: ToDoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //make edittext refer to the actual edittext in activity_main layout
        toDoEditText = findViewById<View>(R.id.toDoEditText) as EditText

        //make recyclerview refer to the actual recyclerview in activity_main layout
        toDoRecyclerView = findViewById<View>(R.id.toDoRecyclerView) as RecyclerView

        //fully initialize the dbhandler
        dbHandler = DBHandler(this, null)

        //initialize the todoadapter
        toDoAdapter = ToDoAdapter(dbHandler!!.todos)

        //tell kotlin that recyclerview isn't null and set todoadapter on it
        toDoRecyclerView!!.adapter = toDoAdapter

        //tell kotlin that recyclerview isn't null and apply a linear layout to it
        toDoRecyclerView!!.layoutManager = LinearLayoutManager(this)
    }

    /**
     * This function gets called when the add button is clicked. It adds a todo into the database.
     * @param view Mainactivity view
     */
    fun addTodo(view: View?) {
        //tell kotlin that edittext isn't null
        //get text input in edittext as string
        //store it in variable
        val todoName = toDoEditText!!.text.toString()

        //trim variable and check if its equal to empty string
        if (todoName.trim() == ""){
            //display please enter a todo toast
            Toast.makeText(this, "Please enter a Todo!", Toast.LENGTH_LONG).show()
        } else {
            //ask kotlin to check if the dbhandler is null
                //if it's not, apply all the code in the let block to it
                    // in the let block, may refer to the dbhandler as it
           dbHandler?.let {
                //call method in todoadapter that will add todo
               //into the database
                toDoAdapter.addTodo(it, todoName)
           }
            //display todo added toast
            Toast.makeText(this, "Todo Added!", Toast.LENGTH_LONG).show()

            //clear edittext
            toDoEditText!!.text.clear()

            //call notifyadapter method
            notifyAdapter()
        }
    }
    /**
     * This function gets called when the delete button is clicked. It deletes a todo into the database.
     * @param view Mainactivity view
     */
    fun deleteTodo(view: View?) {

    }

    /**
     * This method updates the mutablelists of todos in the todoadapter
     * with the most current data in the database
     */
    private fun notifyAdapter(){
        //tell kotlin that dbhandler isn't null
        //call itsgetter method that returns the data in the db as mutable list
        //assign mutable list to the todoadapters mutablelist of todos
        toDoAdapter.todos = dbHandler!!.todos
    }
}