package com.example.todoapp.interfaces

import com.example.todoapp.models.Todo

interface OnTodoItemClickListener {
    fun onItemFinalizadoClick(todo: Todo)
    fun onItemDeleteClick(todo: Todo)
}