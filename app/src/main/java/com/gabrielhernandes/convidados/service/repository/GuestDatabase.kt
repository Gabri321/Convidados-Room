package com.gabrielhernandes.convidados.service.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gabrielhernandes.convidados.service.data.GuestModel

@Database(entities = [GuestModel::class], version = 1)

abstract class GuestDatabase : RoomDatabase() {

    abstract fun getDao(): GuestDao

    companion object {
        lateinit var INSTANCE: GuestDatabase

        fun getDatabase(context: Context): GuestDatabase {
            if (!::INSTANCE.isInitialized) {
                synchronized(GuestDatabase::class.java) {
                    INSTANCE =
                        Room.databaseBuilder(context, GuestDatabase::class.java, "convidados")
                            .allowMainThreadQueries()
                            .build()
                }

            }
            return INSTANCE
        }
    }
}

