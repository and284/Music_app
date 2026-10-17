package com.example.music_app4b

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Declaración de los elementos de la pantalla
    private lateinit var etCorreo: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnIngresar: Button

    // Objeto para trabajar con la base de datos
    private lateinit var dbHerper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Enlazar el texto para crear una cuenta
        val tvCrearCuenta =
            findViewById<TextView>(R.id.tvCrearCuenta)

        // Enlazar los campos de texto
        etCorreo = findViewById(R.id.etCorreo)
        etPassword = findViewById(R.id.etPass)

        // Enlazar el botón de inicio de sesión
        btnIngresar = findViewById(R.id.btnIngresar)

        // Inicializar la conexión con la base de datos
        dbHerper = DatabaseHelper(this)

        // Evento para iniciar sesión al pulsar el botón
        btnIngresar.setOnClickListener {
            iniciarSesion()
        }

        // Evento para ir a la pantalla de registro
        tvCrearCuenta.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    // Método para validar e iniciar sesión
    private fun iniciarSesion() {

        // Obtener el correo y la contraseña escritos
        val correo = etCorreo.text.toString().trim()
        val pass = etPassword.text.toString().trim()

        // Comprobar que los campos no estén vacíos
        if (correo.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "Complete todos los campos",
                Toast.LENGTH_SHORT).show()
            return
        }

        // Consultar las credenciales en la base de datos
        val resultado = dbHerper.iniciarSesion(correo, pass)

        // Comprobar el resultado    del inicio de sesión
        if (resultado) {

            // Si los datos son correctos
            Toast.makeText(this, "¡Bienvenido!", Toast.LENGTH_SHORT).show()

            // Abrir la pantalla principal
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            // Cerrar la pantalla de inicio de sesión
            finish()
        } else {
            // Si las credenciales son incorrectas
            Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
        }
    }
}