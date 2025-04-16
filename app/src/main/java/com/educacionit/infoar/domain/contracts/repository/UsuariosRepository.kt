package com.educacionit.infoar.domain.contracts.repository

import com.educacionit.infoar.domain.models.Usuario

interface UsuariosRepository {
    suspend fun getUsuarios(): List<Usuario>
}