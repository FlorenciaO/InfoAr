package com.educacionit.infoar.data

import com.educacionit.infoar.domain.models.Usuario
import com.educacionit.infoar.domain.repository.UsuariosRepository
import com.educacionit.infoar.data.remote.ServiceApiProvider
import com.educacionit.infoar.data.remote.dto.toUsuario

class UsuariosRepositoryImpl: UsuariosRepository {

    // TODO(definir los daos y los services)


    override suspend fun getUsuarios(): List<Usuario> {
        val serviceApi = ServiceApiProvider.service

        return serviceApi.getUsers().map { it.toUsuario() }
    }
}