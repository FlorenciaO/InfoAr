package com.educacionit.infoar.repository

import com.educacionit.infoar.models.Noticia

interface NoticiasRepository {
    suspend fun getNoticias(): List<Noticia>
}