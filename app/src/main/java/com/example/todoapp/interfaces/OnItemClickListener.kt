package com.example.todoapp.interfaces

import com.example.todoapp.models.Todo

interface OnTodoItemClickListener {
    fun onItemFinalizadoClick(todo: Todo)
    fun onItemDeleteClick(todo: Todo)
    fun onItemClick(todo: Todo)
    fun onItemEditClick(todo: Todo)
}