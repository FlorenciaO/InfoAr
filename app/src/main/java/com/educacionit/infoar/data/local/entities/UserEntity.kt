package com.educacionit.infoar.data.local.entities

import com.educacionit.infoar.data.local.entities.UserEntity.Companion.TABLE_NAME
import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable(tableName = TABLE_NAME)
data class UserEntity(

    // Primary key
    @DatabaseField(id = true)
    val id: String = "",

    @DatabaseField
    val userName: String = "",

    @DatabaseField
    val companyName: String = "",

    @DatabaseField(foreign = true, foreignAutoCreate = true)
    val address: AddressEntity? = null
) {
    companion object {
        const val TABLE_NAME = "usuarios_table"
    }
}
