package com.educacionit.infoar.dominio.models

data class Noticia(
    val id: String,
    val title: String,
    val content: String,
    val image: String,
    val byUser: String
)
