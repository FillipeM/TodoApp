package com.example.todoapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.holders.TodoListItemHolder
import com.example.todoapp.interfaces.OnTodoItemClickListener
import com.example.todoapp.models.Todo

class TodoAdapter(private val todos: MutableList<Todo>, val clickListener: OnTodoItemClickListener, val context: Context) :
    RecyclerView.Adapter<TodoListItemHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListItemHolder {
        val li = LayoutInflater.from(parent.context)
        val view = li.inflate(R.layout.todo_list_item, parent, false)
        return TodoListItemHolder(view)
    }

    override fun onBindViewHolder(holder: TodoListItemHolder, position: Int) {
        val item = todos[position]
        holder.bindTodo(item, clickListener, context)
    }

    override fun getItemCount() = todos.size


}