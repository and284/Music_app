package com.example.music_app4b.model

//ARCHIVO PARA RECIBIR LA RESPUESTA DE CONFIRMACION DEL API
data class ITunesResponse(
    val resultCount : Int,
    val results : List<Album>
)
