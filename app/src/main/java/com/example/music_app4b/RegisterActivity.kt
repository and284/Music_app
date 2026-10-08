package com.example.music_app4b

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class RegisterActivity : AppCompatActivity() {
    //Aqui se declaran los elementos,    lateinit
    private lateinit var etNombre : EditText
    private lateinit var etCorreo : EditText
    private lateinit var etpassword : EditText
    private lateinit var btnRegistrar : EditText

    //Crear instancia del DatabaseHaper
    private lateinit var dbHelper: DatabaseHelper


    //metodo principal
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        //Inicalizar los objetos de los componentes
        etNombre = findViewById(R.id.etNombre)
        etCorreo = findViewById(R.id.etCorreo)
        etpassword = findViewById(R.id.etPass)
        btnRegistrar = findViewById(R.id.btnRegistrar)

        //Inicializar la base de datos en un objeto
        dbHelper = DatabaseHelper(this)

        //Evento para registrar al usuario
        btnRegistrar.setOnClickListener {
            //Registrar al usuario
            registrarUsuario()
        }
    }

    //metodos por aqui
    private fun  registrarUsuario(){
        // variables para guardar los textos
        val nombre = etNombre.text.toString().trim()
        val correo = etCorreo.text.toString().trim()
        val password = etpassword.text.toString().trim()

        if (nombre.isEmpty() || correo.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            return
        }
        //variable que guarda el resultado del registro y llama a la BD
        val registro = dbHelper.registrarUsuario(nombre,correo,password)

        //Validar que el resultado de la insercion sea veraddero o falso
        if (registro){
            //si el registro es correcto
            Toast.makeText(this, "CRegistro exitoso", Toast.LENGTH_SHORT).show()
            //cierra la pantalla actual y regresa a la pantalla anterior
            finish()
        }else{
            Toast.makeText(this, "Registro", Toast.LENGTH_SHORT).show()
        }
    }
}
