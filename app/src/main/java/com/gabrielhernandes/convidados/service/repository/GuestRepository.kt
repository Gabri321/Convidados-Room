package com.gabrielhernandes.convidados.service.repository

import android.content.Context
import com.gabrielhernandes.convidados.service.data.GuestModel

class GuestRepository(context: Context) {

    private var mDatabase: GuestDao = GuestDatabase.getDatabase(context).getDao()


    fun save(guestModel: GuestModel): Boolean {
        return mDatabase.save(guestModel) > 0
    }


    fun update(guestModel: GuestModel): Boolean {

        return mDatabase.update(guestModel) > 0
    }

    fun delete(guestModel: GuestModel) {

        mDatabase.delete(guestModel)
    }

    fun get(id: Int): GuestModel {

       return  mDatabase.get(id)

    }

    fun getList(): List<GuestModel> {
        return mDatabase.getInvited()
    }

    fun getPresent(): List<GuestModel> {

        return mDatabase.getPresent()


    }

    fun getAbsents(): List<GuestModel> {

        return mDatabase.getAbsent()

    }

}