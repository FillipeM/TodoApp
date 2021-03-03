package com.example.todoapp.holders

import android.R
import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.interfaces.OnTodoItemClickListener
import com.example.todoapp.models.Todo
import kotlinx.android.synthetic.main.todo_list_item.view.*

class TodoListItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindTodo(todo: Todo, clickListener: OnTodoItemClickListener, context: Context) {
        itemView.tvTitulo.text = todo.titulo
        itemView.tvDescricao.text = todo.descricao

        try {
            if (todo.indFinalizado) {
                itemView.tvTitulo.setTextColor(Color.GREEN)
                itemView.tvDescricao.setTextColor(Color.GREEN)
                itemView.btnFinalizarTodo.foreground = getDrawable(context, R.drawable.checkbox_on_background)
            }
        } catch (ex: Exception) {
            Toast.makeText(itemView.context, ex.message, Toast.LENGTH_LONG).show()
        }
        itemView.btnFinalizarTodo.setOnClickListener(View.OnClickListener {
            clickListener.onItemFinalizadoClick(todo)
        })

        itemView.btnExcluirTodo.setOnClickListener(View.OnClickListener {
            clickListener.onItemDeleteClick(todo)
        })

        itemView.setOnClickListener(View.OnClickListener { clickListener.onItemClick(todo) })
    }
}