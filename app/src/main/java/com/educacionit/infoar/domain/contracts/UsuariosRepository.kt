package com.educacionit.infoar.domain.contracts

import com.educacionit.infoar.domain.models.Usuario

interface UsuariosRepository {
    suspend fun getUsuarios(): List<Usuario>
}