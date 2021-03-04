package com.example.todoapp.holders

import android.R
import android.content.Context
import android.graphics.Color
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.interfaces.OnTodoItemClickListener
import com.example.todoapp.models.Todo
import kotlinx.android.synthetic.main.todo_list_item.view.*

class TodoListItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener,
    View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
    lateinit var listener: OnTodoItemClickListener
    private var todo: Todo? = null
    fun bindTodo(todo: Todo, clickListener: OnTodoItemClickListener, context: Context) {
        itemView.tvTitulo.text = todo.titulo
        itemView.tvDescricao.text = todo.descricao

        listener = clickListener
        this.todo = todo

        try {
            if (todo.indFinalizado) {
                itemView.tvTitulo.setTextColor(Color.GREEN)
                itemView.tvDescricao.setTextColor(Color.GREEN)
                itemView.btnFinalizarTodo.foreground = getDrawable(
                    context,
                    R.drawable.checkbox_on_background
                )
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

        itemView.setOnClickListener(this)
        itemView.setOnCreateContextMenuListener(this)

    }

    override fun onClick(v: View?) {
        if (todo != null) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(todo!!)
            }
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menu?.setHeaderTitle("SELECIONE UMA OPÇÃO")
        val finalizaMenu = menu?.add(Menu.NONE, 1, 1, "Finalizar")
        val deleteMenu = menu?.add(Menu.NONE, 2, 2, "Excluir")
        val editMenu = menu?.add(Menu.NONE, 3, 3, "Editar")

        finalizaMenu?.setOnMenuItemClickListener(this)
        deleteMenu?.setOnMenuItemClickListener(this)
        editMenu?.setOnMenuItemClickListener(this)
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        if (todo != null) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                when (item?.itemId) {
                    1 -> {
                        listener.onItemFinalizadoClick(todo!!)
                        return true
                    }
                    2 -> {
                        listener.onItemDeleteClick(todo!!)
                        return true
                    }
                    3->{
                        listener.onItemEditClick(todo!!)
                        return true
                    }
                }
            }
        }
        return false
    }
}