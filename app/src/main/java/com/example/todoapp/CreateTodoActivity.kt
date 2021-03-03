package com.example.todoapp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.todoapp.data.TodoDataBaseHelper
import com.example.todoapp.models.Todo
import kotlinx.android.synthetic.main.activity_create_todo.*

class CreateTodoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_todo)

        val intent = intent
        val todo = intent.getSerializableExtra("todo") as Todo?
        if (todo != null) {
            carregaTela(todo)
        }

        btnSalvar.setOnClickListener(View.OnClickListener {
            if (validate()) {
                saveToDo(todo?.id ?: 0)
            }
        })
        btnCancelar.setOnClickListener(View.OnClickListener { this.finish() })
    }

    private fun carregaTela(todo: Todo) {
        editTitulo.setText(todo.titulo.toCharArray(), 0, todo.titulo.length)
        editDescricao.setText(todo.descricao.toCharArray(), 0, todo.descricao.length)
    }

    private fun saveToDo(todoId: Int) {
        val db = TodoDataBaseHelper(this)
        try {
            val todo = Todo(
                id = todoId,
                titulo = editTitulo.text.toString(),
                descricao = editDescricao.text.toString(),
                indFinalizado = false
            )
            if (todo.id == 0) {
                db.insertData(todo)
            } else {
                db.updateData(todo)
            }


            Toast.makeText(this, "Item inserido com sucesso", Toast.LENGTH_LONG).show()
            this.finish()
        } catch (ex: Exception) {
            Toast.makeText(this, ex.message, Toast.LENGTH_LONG).show()
        }
    }

    private fun validate(): Boolean {
        if (editTitulo.text.isEmpty() || editTitulo.text.isBlank()) {
            Toast.makeText(this, "Informe o título do TO-DO", Toast.LENGTH_LONG).show()
            return false;
        }

        if (editDescricao.text.isEmpty() || editDescricao.text.isBlank()) {
            Toast.makeText(this, "Informe a descrição do TO-DO", Toast.LENGTH_LONG).show()
            return false;
        }
        return true
    }
}