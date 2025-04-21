package com.educacionit.infoar.domain.contracts.vistas

import com.educacionit.infoar.domain.models.Usuario

interface UsuariosView {
    fun showLoading()
    fun hideLoading()
    fun showList(usuarios: List<Usuario>)
}