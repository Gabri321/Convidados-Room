package com.gabrielhernandes.convidados.service.constants

class GuestConstants private constructor() {

    companion object {
        const val ID = "guestID"
    }

    object FILTER {
        const val ALL = 0
        const val PRESENT = 1
        const val ABSENT = 2

    }
}