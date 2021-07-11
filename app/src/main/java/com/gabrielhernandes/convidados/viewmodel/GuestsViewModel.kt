package com.gabrielhernandes.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gabrielhernandes.convidados.service.constants.GuestConstants
import com.gabrielhernandes.convidados.service.data.GuestModel
import com.gabrielhernandes.convidados.service.repository.GuestFormRepository

class GuestsViewModel(application: Application) : AndroidViewModel(application) {

    private var mGuestFormRepository: GuestFormRepository =
        GuestFormRepository.getInstance(application.applicationContext)

    private val mGuestList = MutableLiveData<List<GuestModel>>()

    var guestList: LiveData<List<GuestModel>> = mGuestList

    fun load(filter: Int){

        if(filter.equals(GuestConstants.FILTER.ALL)) {

            mGuestList.value = mGuestFormRepository.getList()
        }
        else if(filter.equals(GuestConstants.FILTER.PRESENT)){
            mGuestList.value = mGuestFormRepository.getPresent()

        }
        else {
            mGuestList.value = mGuestFormRepository.getAbsents()
        }
    }

    fun delete(id:Int){
        mGuestFormRepository.delete(id)
    }
}