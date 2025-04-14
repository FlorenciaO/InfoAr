package com.educacionit.infoar.domain.repository

import com.educacionit.infoar.domain.models.Noticia

interface NoticiasRepository {
    suspend fun getNoticias(): List<Noticia>
}