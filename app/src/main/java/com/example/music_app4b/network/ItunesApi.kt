package com.example.music_app4b.network

import com.example.music_app4b.model.ITunesResponse
import retrofit2.http.GET
import retrofit2.http.Query

//ARCHIVOS PARA LA CONSULTA DE INFORMACION(LOCAL O INTERNET)
interface ItunesApi {

    //Indicar metodo HTTP a usar
    @GET("search")

    //metodo para obtener la informacion con la URL del API
    fun obtenerAlbumGenero(
        @Query("term") genero: String,
        @Query("country") pais: String,
        @Query("media") media : String,
        @Query("entity") entidad : String,
        @Query("limit") limite : Int,
    ) : retrofit2.Call<ITunesResponse>
}