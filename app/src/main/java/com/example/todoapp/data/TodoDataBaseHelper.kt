package com.example.todoapp.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.todoapp.models.Todo

const val DATABASE_NAME = "todoDB"
const val DATABASE_VERSION = 1
const val TABLENAME = "todos"
const val COL_CODIGO = "codTodo"
const val COL_TITULO = "tituloTodo"
const val COL_DESCRICAO = "descTodo"
const val COL_FINALIZADO = "indTodoFinalizado"

class TodoDataBaseHelper(val context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val command = "create table $TABLENAME($COL_CODIGO integer primary key autoincrement , $COL_TITULO varchar(50), $COL_DESCRICAO varchar(255), $COL_FINALIZADO bit default(0))"
        db?.execSQL(command)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val command = "DROP TABLE IF EXISTS $TABLENAME"
        db?.execSQL(command)
        onCreate(db)
    }

    fun insertData(todo:Todo){
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_TITULO, todo.titulo)
        cv.put(COL_DESCRICAO, todo.descricao)
        cv.put(COL_FINALIZADO, todo.indFinalizado)

        val result = db.insert(TABLENAME, null, cv)
    }

    fun readData(): MutableList<Todo>{
        val db = this.readableDatabase
        val list: MutableList<Todo> = ArrayList()
        val command = "Select * from $TABLENAME"
        val result = db.rawQuery(command, null)
        if (result.moveToFirst()){
            do{
                val codigo = result.getString(result.getColumnIndex(COL_CODIGO)).toInt()
                val titulo = result.getString(result.getColumnIndex(COL_TITULO))
                val descricao = result.getString(result.getColumnIndex(COL_DESCRICAO))
                val finalizado = result.getString(result.getColumnIndex(COL_FINALIZADO)) == "1"

                list.add(Todo(codigo, titulo, descricao, finalizado))
            }while (result.moveToNext())
        }
        return list;
    }

    fun updateData(todo: Todo ){
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_CODIGO, todo.id)
        cv.put(COL_TITULO, todo.titulo)
        cv.put(COL_DESCRICAO, todo.descricao)
        cv.put(COL_FINALIZADO, todo.indFinalizado)
        val where = "$COL_CODIGO = ${todo.id}"
        try {
            db.update(TABLENAME, cv, where, emptyArray())
        }catch (ex: Exception)
        {
            Toast.makeText(context, ex.message, Toast.LENGTH_LONG).show()
        }
    }

    fun deleteData(todo: Todo){
        val db = this.writableDatabase
        val where = "$COL_CODIGO = ${todo.id}"
        try {
            db.delete(TABLENAME, where, emptyArray())
        }catch (ex: Exception){
            Toast.makeText(context, ex.message, Toast.LENGTH_LONG).show()
        }
    }
}