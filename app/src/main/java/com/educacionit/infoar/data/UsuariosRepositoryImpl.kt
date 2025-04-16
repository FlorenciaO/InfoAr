package com.educacionit.infoar.data

import android.content.Context
import android.util.Log
import com.educacionit.infoar.data.local.db.DBHelper
import com.educacionit.infoar.data.local.entities.UserEntity
import com.educacionit.infoar.data.remote.dto.toUserEntity
import com.educacionit.infoar.domain.models.Usuario
import com.educacionit.infoar.domain.contracts.UsuariosRepository
import com.educacionit.infoar.data.remote.service.ServiceApiProvider
import com.educacionit.infoar.data.remote.dto.toUsuario
import com.educacionit.infoar.data.remote.service.ServiceAPI
import com.j256.ormlite.android.apptools.OpenHelperManager
import com.j256.ormlite.dao.Dao
import java.sql.SQLException



class UsuariosRepositoryImpl(context: Context): UsuariosRepository {

    companion object {
        const val TAG = "UsuariosRepositoryImpl"
    }

    private lateinit var dao: Dao<UserEntity, Int>
    private var serviceApi: ServiceAPI

    init {
        serviceApi = ServiceApiProvider.service
        val helper = OpenHelperManager.getHelper(context, DBHelper::class.java)
        try {
            dao = helper.getDao(UserEntity::class.java)
        } catch (e: SQLException) {
            Log.d(TAG, e.localizedMessage.orEmpty())
        }
    }


    override suspend fun getUsuarios(): List<Usuario> {
        val listaDeUsuarios = serviceApi.getUsers() // DTO

        if (listaDeUsuarios.isNotEmpty()) {
            val listaDeUserEntities = listaDeUsuarios.map { it.toUserEntity() }
            dao.create(listaDeUserEntities)
        }

        return listaDeUsuarios.map { it.toUsuario() }
    }
}