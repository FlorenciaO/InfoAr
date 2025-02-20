package com.educacionit.infoar.data.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.support.ConnectionSource

class DBHelper(
    context: Context
) : OrmLiteSqliteOpenHelper(
    context,
    NOMBRE_DB,
    null,
    VERSION_DB
) {

    override fun onCreate(database: SQLiteDatabase?, connectionSource: ConnectionSource?) {
        // TODO (crear 2 tablas (noticias y usuarios))
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
    }
}