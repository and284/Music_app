package com.example.music_app4b

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.music_app4b.model.Album
import com.example.music_app4b.model.ITunesResponse
import com.example.music_app4b.network.RetrofinClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {

    private lateinit var tvResultado : TextView
    private lateinit var contenedorIndie : LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        tvResultado = findViewById(R.id.tvResultado)
        contenedorIndie = findViewById(R.id.contenedorIndie)

        //TOD: Agregar metodo de llamado de Album
        obtenerAlbum()

    }

    //METODO PARA OBTENER INFROMACION DEL ALBUM
    private fun obtenerAlbum(){

        //hacer el llamado a la construccion de la URL
        RetrofinClient.api.obtenerAlbumGenero(
            genero = "Romeo Santos",
            pais = "MX",
            media = "music",
            entidad = "album",
            limite = 1
        ).enqueue( object : Callback<ITunesResponse?> {
            //METODO EN CASO DE QUE LA RESPUESTA SEA CORRECTA
            override fun onResponse(call: Call<ITunesResponse?>, response: Response<ITunesResponse?>) {
                //Variable para guardar el numero de albumes consultados
                val albumes = response.body()?.results

                //Validar si la variable albumes tiene informacion
                if(!albumes.isNullOrEmpty()){

                    //Variable para acceder al primer elemneto de los albumes encontrados
                    val album = albumes[0]

                    tvResultado.text =
                        "Album: ${ album.collectionName } \n\n" +
                                "Artista: ${album.artistName} \n\n"+
                                "Numero canciones: ${album.trackCount} \n\n"
                    //Mostrar la imagen del abum
                    /*Glide.with(this@HomeActivity)
                        .load(album.obtenerImagenGrande())
                        .into(imgAlbum)*/

                }else{
                    Toast.makeText(this@HomeActivity, "No hay respuesta del API", Toast.LENGTH_SHORT).show()
                }

            }
            //METODO PARA HACER UNA ACIION SI EL API FALLA
            override fun onFailure(call: Call<ITunesResponse?>, t: Throwable) {
                Toast.makeText(this@HomeActivity, "Error en el API", Toast.LENGTH_SHORT).show()
            }
        })
    }

    //METODO PARA DEMOSTRAR EL RESULTADO DE DATOS DE UN API
    private fun mostrarAlbumes(albumes : List<Album>){

        //OBLIGATORIAMENTE Evitar elementos duplicados
        contenedorIndie.removeAllViews()

        //ciclo para recorrer las multiples particiones aun apip
        for(album in albumes){

            //usar el linerlayout con el contenedor
            val vistaAlbum = layoutInflater.inflate(
                R.layout.item_album,
                contenedorIndie,
                false
            )
            val imgPortada = vistaAlbum.findViewById<ImageView>(R.id.imgPortadaAlbum)
            val tvNombreAlbum = vistaAlbum.findViewById<TextView>(R.id.tvNombreAlbum)
            val tvNombreArtista = vistaAlbum.findViewById<TextView>(R.id.tvNombreArtista)

            //Asignando los nombre atenidos del API
            tvNombreAlbum.text = album.collectionName ?: "Album sin nombre"
            tvNombreArtista.text = album.artistName ?: "Artista desconocido"


            Glide.with(this@HomeActivity)
                .load(album.obtenerImagenGrande())
                .into(imgPortada)

            //Asignar la vista del contenedor a las tajetas de albumen
            contenedorIndie.addView(vistaAlbum)

        }

    }
}