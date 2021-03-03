package com.example.todoapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.adapters.TodoAdapter
import com.example.todoapp.data.TodoDataBaseHelper
import com.example.todoapp.interfaces.OnTodoItemClickListener
import com.example.todoapp.models.Todo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnTodoItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdd.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, CreateTodoActivity::class.java)
            startActivity(intent)
        })

        configureRecycler()
    }

    override fun onResume() {
        super.onResume()
        configureRecycler()
    }

    fun configureRecycler() {
        rvTodo.apply {
            adapter = TodoAdapter(getData(), this@MainActivity, this@MainActivity)
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private fun getData(): MutableList<Todo> {
        val db = TodoDataBaseHelper(this)
        return db.readData()
    }

    override fun onItemFinalizadoClick(todo: Todo) {
        todo.indFinalizado = !todo.indFinalizado
        val db = TodoDataBaseHelper(this)
        db.updateData(todo)
        configureRecycler()
    }

    override fun onItemDeleteClick(todo: Todo) {
        val db = TodoDataBaseHelper(this)
        db.deleteData(todo)
        configureRecycler()
    }

    override fun onItemClick(todo: Todo) {
        if (todo.indFinalizado) {
            Toast.makeText(this, "Não é possível alterar um to-do finalizado!", Toast.LENGTH_SHORT).show()
        } else {
            val intent = Intent(this, CreateTodoActivity::class.java)
            intent.putExtra("todo", todo)
            startActivity(intent)
        }

    }

}