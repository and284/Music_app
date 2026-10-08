package com.example.music_app4b

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.contentValuesOf

//Crear clase DatabaseHaper que hereda de la clase SQLite
class DatabaseHelper(context: Context) : SQLiteOpenHelper (
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
){
    //Datos de la base de datos
    companion object{

        //informacion de la BD
        private const val DATABASE_NAME = "musica.db"
        private  const val DATABASE_VERSION = 2

        //Informacion de las tablas en la BD
        private const val TABLE_USUARIOS = "usuarios"
        private const val COL_ID = "id"
        private const val COL_NOMBRE = "nombre"
        private const val COL_CORREO = "correo"
        private const val COL_PASSWORD = "password"
    }

    //METODO ONCREATE --para crear la DB
    override fun onCreate(db: SQLiteDatabase?) {
     //Crear la tabla
        val crearTablaUsuarios = """
            CREATE TABLE $TABLE_USUARIOS(
            $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, 
            $COL_NOMBRE TEXT NOT NULL
            $COL_CORREO TEXT NOT NULL
            $COL_PASSWORD TEXT NOT NULL
            )
        """.trimIndent()
        //Crear tabla de usuarios
        db?.execSQL(crearTablaUsuarios)

    }
    //METODO ONUPGRADE
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USUARIOS")
        onCreate(db)
    }

    //METODO PARA REGISTRAR USUARIO (INSERT)
    fun registrarUsuario(nombre: String, correo: String, password: String) : Boolean{

        //Crear variable par escribir en la db
        val db = writableDatabase

        //Insertar los datos
        val datos = ContentValues().apply {
            put(COL_NOMBRE, nombre)
            put(COL_CORREO, correo)
            put(COL_PASSWORD, password)
        }

        //Ejecutar inserccion de datos  (1L O -1L)
        val resultado = db.insert(TABLE_USUARIOS,null,datos)

        //cerrar conexion de la BD
        db.close()

        //Regresar el valor obtenido de la inserccion (Verdadero o falso)
        return resultado != -1L
    }
}