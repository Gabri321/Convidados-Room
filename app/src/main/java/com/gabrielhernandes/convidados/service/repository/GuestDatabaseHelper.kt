package com.gabrielhernandes.convidados.service.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.gabrielhernandes.convidados.service.constants.DatabaseConstants

class GuestDatabaseHelper(context: Context) : SQLiteOpenHelper(context, NAME, null, VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_GUEST)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }


    companion object {
        private const val VERSION = 1
        private const val NAME = "Convidados.db"

        private const val CREATE_TABLE_GUEST =
            ("create table " + DatabaseConstants.GUEST.TABLE_NAME + " ("
                    + DatabaseConstants.GUEST.COLUMNS.ID + " integer primary key autoincrement, "
                    + DatabaseConstants.GUEST.COLUMNS.NAME + " text, "
                    + DatabaseConstants.GUEST.COLUMNS.PRESENCE + " integer);")
    }
}