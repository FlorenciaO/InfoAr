package com.educacionit.infoar.models

data class Usuario(
    val id: String,
    val userName: String,
    val companyName: String,
    val address: Address
) {
    data class Address(
        val street: String,
        val city: String,
        val lat: Double,
        val lng: Double
    )
}
