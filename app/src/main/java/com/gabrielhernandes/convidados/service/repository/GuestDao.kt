package com.gabrielhernandes.convidados.service.repository

import androidx.room.*
import com.gabrielhernandes.convidados.service.data.GuestModel

@Dao
interface GuestDao {
    @Insert
    fun save(guest: GuestModel): Long

    @Update
    fun update(guest: GuestModel): Int

    @Delete
    fun delete(guest: GuestModel) 

    @Query("SELECT * FROM guest WHERE id = :id")
    fun get(id: Int): GuestModel

    @Query("SELECT * FROM guest")
    fun getInvited(): List<GuestModel>

    @Query("SELECT * FROM guest WHERE present = 1")
    fun getPresent(): List<GuestModel>

    @Query("SELECT * FROM guest WHERE present = 0")
    fun getAbsent(): List<GuestModel>


}



