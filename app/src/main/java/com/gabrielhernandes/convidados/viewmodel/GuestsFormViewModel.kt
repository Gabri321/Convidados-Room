package com.gabrielhernandes.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gabrielhernandes.convidados.service.data.GuestModel
import com.gabrielhernandes.convidados.service.repository.GuestFormRepository

class GuestsFormViewModel(application: Application) : AndroidViewModel(application) {
    private val mContext = application.applicationContext

    private var mGuestFormRepository: GuestFormRepository =
        GuestFormRepository.getInstance(mContext)


    private var mSaveGuest = MutableLiveData<Boolean>()
    val saveGuest: LiveData<Boolean> = mSaveGuest

    private var mGuest = MutableLiveData<GuestModel>()

    var guest: LiveData<GuestModel> = mGuest


    fun save(id: Int, name: String, presence: Boolean) {
        val guest = GuestModel(id, name, presence)

        if (id == 0) {
            mSaveGuest.value = mGuestFormRepository.save(guest)
        } else {
            mSaveGuest.value = mGuestFormRepository.update(guest)
        }
    }

    fun load(id: Int) {
        mGuest.value = mGuestFormRepository.get(id)
    }
}