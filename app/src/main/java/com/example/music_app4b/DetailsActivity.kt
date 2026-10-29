package com.example.music_app4b

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailsActivity : AppCompatActivity() {

    private lateinit var tvResultado : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_details)

        tvResultado = findViewById(R.id.tvResultado)

        mostrarInformacionAlbum()


    }
    private  fun mostrarInformacionAlbum(){

        val idAlbum = intent.getLongExtra("ID_ALBUM", 1)
        val nombreAlbum = intent.getStringExtra("NOMBRE_ALBUM")
        val artista = intent.getStringExtra("ARTISTA_ALBUM")
        val genero = intent.getStringExtra("GENERO_ALBUM")
        val canciones = intent.getIntExtra("CANCIONES_ALBUM", 1)
        val imagen = intent.getStringExtra("IMAGEN_ALBUM")

        tvResultado.text = "Nombre del album: $nombreAlbum"
    }
}