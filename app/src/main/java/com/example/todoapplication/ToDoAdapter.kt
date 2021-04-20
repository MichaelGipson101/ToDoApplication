package com.example.todoapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class ToDoAdapter (
    //declare a mutable list of todos
    var todos: MutableList<ToDo>
    ) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>(){

    //declare a viewholder that will hold the layout of an item in the
    //recycler view
    class ToDoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    //declare textview mutable field using null safety
    var todoTextView: TextView? = null

    //declare checkbox mutable field using null safety
    var todoCheckBox: CheckBox? = null

    /**
     * Create a todoviewholder that references the li_main layout
     * resource and return it.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        return ToDoViewHolder(LayoutInflater.from(parent.context).inflate(
                R.layout.li_main,
                parent,
                false
        ))
    }

    /**
     * Initializes each of the items in the recyclerview and maps the
     * data in the mutablelist of todos to it
     */
    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        //get current item in mutablelist of todos and store it
        //in an immutable var
        val currentToDo = todos[position]

        holder.itemView.apply {
            //make textview refer to textview in li_main layout resrc
            todoTextView = findViewById<View>(R.id.toDoTextView) as TextView
            //tell kotlin that textview isn't null
            //assign the name value in the current item to text attr of
            //textview
            todoTextView!!.text = currentToDo.name

            //make checkbox refer to the checkbox in li_main
            todoCheckBox = findViewById<View>(R.id.toDoCheckBox) as CheckBox
            //tell kotlin that checkbox isn't null
            //assign the is_checked value in the current item to text attr of
            //checkbox
            todoCheckBox!!.isChecked = currentToDo.isChecked
            
            //tell kotlin that checkbox isn't null
            //set oncheckedchangelistener to it
            todoCheckBox!!.setOnCheckedChangeListener { _, _ ->  }
            //reverse the value in the ischecked field of the current todo
                currentToDo.isChecked = !currentToDo.isChecked
        }

    }

    /**
     * Returns the number of items in the mutable list of todos
     */
    override fun getItemCount(): Int {
        return todos.size
    }

    /**
     * This method gets called by the addtodo method in the mainactivity
     * when the add button is clicked. It will call the dbhandler method
     * that adds a todo into the db
     */
    fun addTodo(dbHandler: DBHandler, name: String) {
        //ask kotlin to check if the dbhandler is null
        //if it isn't, call its addtodo method passing the
        //specified todo name
        dbHandler?.addTodo(name)
        notifyDataSetChanged()
    }


}