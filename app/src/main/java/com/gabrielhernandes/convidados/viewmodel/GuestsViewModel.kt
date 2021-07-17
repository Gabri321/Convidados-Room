package com.gabrielhernandes.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gabrielhernandes.convidados.service.constants.GuestConstants
import com.gabrielhernandes.convidados.service.data.GuestModel
import com.gabrielhernandes.convidados.service.repository.GuestRepository

class GuestsViewModel(application: Application) : AndroidViewModel(application) {

    private var mGuestRepository: GuestRepository =
        GuestRepository(application.applicationContext)

    private val mGuestList = MutableLiveData<List<GuestModel>>()

    var guestList: LiveData<List<GuestModel>> = mGuestList

    fun load(filter: Int) {

        when {
            filter == GuestConstants.FILTER.ALL -> {

                mGuestList.value = mGuestRepository.getList()
            }
            filter.equals(GuestConstants.FILTER.PRESENT) -> {
                mGuestList.value = mGuestRepository.getPresent()

            }
            else -> {
                mGuestList.value = mGuestRepository.getAbsents()
            }
        }
    }

    fun delete(id: Int) {

        var guest = mGuestRepository.get(id)
        mGuestRepository.delete(guest)
    }
}