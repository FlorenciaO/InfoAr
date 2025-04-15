package com.educacionit.infoar.data.local.entities

import com.educacionit.infoar.data.local.entities.AddressEntity.Companion.TABLE_NAME
import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable


@DatabaseTable(tableName = TABLE_NAME)
data class AddressEntity(

    @DatabaseField(generatedId = true)
    val id: Int = 0,

    @DatabaseField
    val street: String = "",

    @DatabaseField
    val city: String = "",

    @DatabaseField
    val lat: Double = 0.0,

    @DatabaseField
    val lng: Double = 0.0
) {
    companion object {
        const val TABLE_NAME = "address_table"
    }
}