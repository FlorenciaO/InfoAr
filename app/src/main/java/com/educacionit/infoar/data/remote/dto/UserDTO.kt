package com.educacionit.infoar.data.remote.dto

import com.educacionit.infoar.data.local.entities.AddressEntity
import com.educacionit.infoar.data.local.entities.UserEntity
import com.educacionit.infoar.domain.models.Usuario

data class UserDTO(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val address: AddressDTO,
    val company: Company,
) {
    data class AddressDTO(
        val street: String,
        val city: String,
        val geo: Geo,
    )

    data class Geo(
        val lat: String,
        val lng: String,
    )

    data class Company(val bs: String, val catchPhrase: String, val name: String)
}

fun UserDTO.toUserEntity(): UserEntity {
    return UserEntity(
        id = id.toString(),
        userName = username,
        companyName = company.name,
        address = address.toAddressEntity()
    )
}

fun UserDTO.AddressDTO.toAddressEntity(): AddressEntity {
    return AddressEntity(
        street = street,
        city = city,
        lat = geo.lat.toDouble(),
        lng = geo.lng.toDouble()
    )
}

fun UserDTO.toUsuario(): Usuario {
    return Usuario(
        id = id.toString(),
        userName = username,
        companyName = company.name,
        address = address.toAddress()
    )
}

fun UserDTO.AddressDTO.toAddress(): Usuario.Address {
    return Usuario.Address(
        street = street,
        city = city,
        lat = geo.lat.toDouble(),
        lng = geo.lng.toDouble()
    )
}