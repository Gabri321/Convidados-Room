package com.gabrielhernandes.convidados.service.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "guest")
class GuestModel() {
    //var id : Int , var name: String, var present: Boolean

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

    @ColumnInfo(name = "name")
    var name: String = ""

    @ColumnInfo(name = "present")
    var present = true

}
