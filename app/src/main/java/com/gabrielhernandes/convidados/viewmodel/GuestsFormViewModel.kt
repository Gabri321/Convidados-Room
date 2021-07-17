package com.gabrielhernandes.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gabrielhernandes.convidados.service.data.GuestModel
import com.gabrielhernandes.convidados.service.repository.GuestRepository

class GuestsFormViewModel(application: Application) : AndroidViewModel(application) {
    private val mContext = application.applicationContext

    private var mGuestRepository: GuestRepository = GuestRepository(mContext)


    private var mSaveGuest = MutableLiveData<Boolean>()
    val saveGuest: LiveData<Boolean> = mSaveGuest

    private var mGuest = MutableLiveData<GuestModel>()

    var guest: LiveData<GuestModel> = mGuest


    fun save(id: Int, name: String, present: Boolean) {
        val guest = GuestModel().apply {
            this.id = id
            this.name = name
            this.present = present
        }

        if (id == 0) {
            mSaveGuest.value = mGuestRepository.save(guest)
        } else {
            mSaveGuest.value = mGuestRepository.update(guest)
        }
    }

    fun load(id: Int) {
        mGuest.value = mGuestRepository.get(id)
    }
}