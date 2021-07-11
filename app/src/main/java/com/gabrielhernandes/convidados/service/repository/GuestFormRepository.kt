package com.gabrielhernandes.convidados.service.repository

import android.content.ContentValues
import android.content.Context
import com.gabrielhernandes.convidados.service.constants.DatabaseConstants
import com.gabrielhernandes.convidados.service.data.GuestModel

class GuestFormRepository private constructor(context: Context) {

    private var mDatabaseHelper:GuestDatabaseHelper = GuestDatabaseHelper(context)

    companion object {
        lateinit var repository: GuestFormRepository

        fun getInstance(context: Context): GuestFormRepository {
            if (!::repository.isInitialized) {
                repository = GuestFormRepository(context)
            }

            return repository
        }
    }

    fun save(guestModel: GuestModel): Boolean {

        return try {


            var database = mDatabaseHelper.writableDatabase

            var contentValues = ContentValues()

            contentValues.put(DatabaseConstants.GUEST.COLUMNS.NAME, guestModel.name)

            contentValues.put(DatabaseConstants.GUEST.COLUMNS.PRESENCE, guestModel.present)

            database.insert(DatabaseConstants.GUEST.TABLE_NAME, null, contentValues)
            true
        } catch (e: Exception) {
            return false
        }

    }


    fun update(guestModel: GuestModel): Boolean {


        return try {


            var database = mDatabaseHelper.writableDatabase

            var contentValues = ContentValues()

            contentValues.put(DatabaseConstants.GUEST.COLUMNS.NAME, guestModel.name)

            contentValues.put(DatabaseConstants.GUEST.COLUMNS.PRESENCE, guestModel.present)

            var clause = DatabaseConstants.GUEST.COLUMNS.ID + " = ?"

            var args = arrayOf(guestModel.id.toString())

            database.update(DatabaseConstants.GUEST.TABLE_NAME, contentValues, clause, args)
            true
        } catch (e: Exception) {
            false
        }

    }

    fun delete(id: Int): Boolean {
        return try {
            var database = mDatabaseHelper.writableDatabase

            var clause = DatabaseConstants.GUEST.COLUMNS.ID + " = ?"

            var args = arrayOf(id.toString())

            database.delete(DatabaseConstants.GUEST.TABLE_NAME, clause, args)
            true
        } catch (e: Exception) {
            false
        }

    }

    fun get(id: Int): GuestModel? {

        var guest: GuestModel? = null
        return try {
            var database = mDatabaseHelper.readableDatabase

            var projection = arrayOf(
                DatabaseConstants.GUEST.COLUMNS.NAME,
                DatabaseConstants.GUEST.COLUMNS.PRESENCE
            )


            var selection = DatabaseConstants.GUEST.COLUMNS.ID + " = ?"

            var args = arrayOf(id.toString())

            var cursor = database.query(
                DatabaseConstants.GUEST.TABLE_NAME,
                projection,
                selection,
                args,
                null,
                null,
                null
            )

            if (cursor.count > 0 && cursor != null) {
                cursor.moveToFirst()
                val name =
                    cursor.getString(cursor.getColumnIndex(DatabaseConstants.GUEST.COLUMNS.NAME))
                val presence =
                    (cursor.getInt(cursor.getColumnIndex(DatabaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)


                guest = GuestModel(id, name, presence)
            }
            cursor?.close()
            guest
        } catch (e: Exception) {
            guest
        }

    }

    fun getList(): List<GuestModel> {

        var list: MutableList<GuestModel> = ArrayList()

        return try {
            var database = mDatabaseHelper.readableDatabase

            var projection = arrayOf(
                DatabaseConstants.GUEST.COLUMNS.NAME,
                DatabaseConstants.GUEST.COLUMNS.PRESENCE,
                DatabaseConstants.GUEST.COLUMNS.ID
            )


            var cursor = database.query(
                DatabaseConstants.GUEST.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
            )

            if (cursor.count > 0 && cursor != null) {
                while (cursor.moveToNext()) {
                    val name =
                        cursor.getString(cursor.getColumnIndex(DatabaseConstants.GUEST.COLUMNS.NAME))
                    val presence =
                        (cursor.getInt(cursor.getColumnIndex(DatabaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                    val id =
                        cursor.getInt(cursor.getColumnIndex(DatabaseConstants.GUEST.COLUMNS.ID))
                    var guest = GuestModel(id, name, presence)

                    list.add(guest)


                }
            }
            cursor?.close()
            list
        } catch (e: Exception) {
            list
        }

        return list

    }

    fun getPresent(): List<GuestModel> {

        var list: MutableList<GuestModel> = ArrayList()

        return try {
            var database = mDatabaseHelper.readableDatabase

            var cursor =
                database.rawQuery("SELECT id,name,presence FROM Guest WHERE presence = 1", null)

            if (cursor.count > 0 && cursor != null) {
                while (cursor.moveToNext()) {
                    val name =
                        cursor.getString(cursor.getColumnIndex(DatabaseConstants.GUEST.COLUMNS.NAME))
                    val presence =
                        (cursor.getInt(cursor.getColumnIndex(DatabaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                    val id =
                        cursor.getInt(cursor.getColumnIndex(DatabaseConstants.GUEST.COLUMNS.ID))
                    var guest = GuestModel(id, name, presence)

                    list.add(guest)


                }
            }
            cursor?.close()
            list
        } catch (e: Exception) {
            list
        }

        return list

    }

    fun getAbsents(): List<GuestModel> {

        var list: MutableList<GuestModel> = ArrayList()

        return try {
            var database = mDatabaseHelper.readableDatabase

            var cursor =
                database.rawQuery("SELECT id,name,presence FROM Guest WHERE presence = 0", null)

            if (cursor.count > 0 && cursor != null) {
                while (cursor.moveToNext()) {
                    val name =
                        cursor.getString(cursor.getColumnIndex(DatabaseConstants.GUEST.COLUMNS.NAME))
                    val presence =
                        (cursor.getInt(cursor.getColumnIndex(DatabaseConstants.GUEST.COLUMNS.PRESENCE)) == 1)

                    val id =
                        cursor.getInt(cursor.getColumnIndex(DatabaseConstants.GUEST.COLUMNS.ID))
                    var guest = GuestModel(id, name, presence)
                    list.add(guest)

                }
            }
            cursor?.close()
            list
        } catch (e: Exception) {
            list
        }

        return list

    }

}