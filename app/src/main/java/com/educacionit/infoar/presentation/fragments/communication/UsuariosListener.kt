package com.educacionit.infoar.presentation.fragments.communication

interface UsuariosListener {
    fun onGoToMapClicked(username: String, address: String, lat: Double, lng: Double)
}