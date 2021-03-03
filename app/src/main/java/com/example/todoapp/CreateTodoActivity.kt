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
        btnSalvar.setOnClickListener(View.OnClickListener {
            if (validate()) {
                val db = TodoDataBaseHelper(this)
                try {
                    db.insertData(
                        Todo(
                            id = 0,
                            titulo = editTitulo.text.toString(),
                            descricao = editDescricao.text.toString(),
                            indFinalizado = false
                        )
                    )
                    Toast.makeText(this, "Item inserido com sucesso", Toast.LENGTH_LONG).show()
                    this.finish()
                }catch (ex: Exception){
                    Toast.makeText(this, ex.message, Toast.LENGTH_LONG).show()
                }
            }
        })
        btnCancelar.setOnClickListener(View.OnClickListener { this.finish() })
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