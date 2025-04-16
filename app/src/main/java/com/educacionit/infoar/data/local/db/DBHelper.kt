package com.educacionit.infoar.data.local.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.educacionit.infoar.data.local.entities.AddressEntity
import com.educacionit.infoar.data.local.entities.UserEntity
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.support.ConnectionSource
import com.j256.ormlite.table.TableUtils

class DBHelper(
    context: Context
) : OrmLiteSqliteOpenHelper(
    context,
    NOMBRE_DB,
    null,
    VERSION_DB
) {

    override fun onCreate(database: SQLiteDatabase?, connectionSource: ConnectionSource?) {
        try {
            TableUtils.createTable(connectionSource, UserEntity::class.java)
            TableUtils.createTable(connectionSource, AddressEntity::class.java)
        } catch (error: Exception) {
            Log.e(TAG, error.localizedMessage.orEmpty())
        }
    }

    override fun onUpgrade(
        database: SQLiteDatabase?,
        connectionSource: ConnectionSource?,
        oldVersion: Int,
        newVersion: Int
    ) {
        // Sin implementación
    }

    private companion object {
        const val NOMBRE_DB = "InfoAr"
        const val VERSION_DB = 1
        const val TAG = "DBHelper"
    }
}