package com.gabrielhernandes.convidados.view.viewholder

import android.app.AlertDialog
import android.app.Dialog
import android.os.Build
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.gabrielhernandes.convidados.R
import com.gabrielhernandes.convidados.service.data.GuestModel
import com.gabrielhernandes.convidados.view.GuestListener
import kotlinx.android.synthetic.main.row_guests.view.*

class GuestViewHolder(itemView: View, private val listener: GuestListener) :
    RecyclerView.ViewHolder(itemView) {


    fun bind(guest: GuestModel) {
        var name = itemView.findViewById<TextView>(R.id.invited_person)

        name.text = guest.name

        name.setOnClickListener {
            listener.onCreate(guest.id)
        }

        name.setOnLongClickListener {
            val builder = AlertDialog.Builder(itemView.context)
            builder.setTitle(R.string.remocao_convidado)
            builder.setMessage(R.string.deseja_remover)
            builder.setPositiveButton(R.string.remover) { dialog, which ->
                listener.onDelete(guest.id)
            }
            builder.setNegativeButton(R.string.cancelar, null)
            builder.show()
            true
        }
    }

}