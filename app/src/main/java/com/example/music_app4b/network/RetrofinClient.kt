package com.example.music_app4b.network

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//ARCHIVO QUE PERMITE TENER UN OBJETO PARA LA CONEXION A INTERNET
object RetrofinClient {

    //Constante para tener la BASE URL
    private const val BASE_URL = "https://itunes.apple.com/"

    //Variable que hereda de "Lazy"
    val api : ItunesApi by lazy {
        //Contruir la URL completa
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ItunesApi::class.java)
    }

}