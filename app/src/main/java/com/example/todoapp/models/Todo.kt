package com.example.todoapp.models

class Todo(val id:Int,
var titulo: String,
var descricao: String,
var indFinalizado: Boolean) {
    override fun toString(): String {
        return titulo;
    }
}