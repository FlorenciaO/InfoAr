package com.educacionit.infoar.models

data class Noticia(
    val id: String,
    val title: String,
    val content: String,
    val image: String,
    val thumbnail: String,
    val updatedAt: String,
    val byUser: String
)
