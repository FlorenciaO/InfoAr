package com.educacionit.infoar.data

import com.educacionit.infoar.models.Usuario
import com.educacionit.infoar.repository.UsuariosRepository
import com.educacionit.infoar.repository.service.ServiceApiProvider
import com.educacionit.infoar.toUsuario

class UsuariosRepositoryImpl: UsuariosRepository {
    override suspend fun getUsuarios(): List<Usuario> {
        val serviceApi = ServiceApiProvider.service

        return serviceApi.getUsers().map { it.toUsuario() }
    }
}