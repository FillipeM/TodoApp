package com.example.todoapp.models

import java.io.Serializable

class Todo(val id:Int,
var titulo: String,
var descricao: String,
var indFinalizado: Boolean) : Serializable {
    override fun toString(): String {
        return titulo;
    }
}