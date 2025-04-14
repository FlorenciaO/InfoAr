package com.educacionit.infoar.repository

import com.educacionit.infoar.models.Usuario

interface UsuariosRepository {
    suspend fun getUsuarios(): List<Usuario>
}