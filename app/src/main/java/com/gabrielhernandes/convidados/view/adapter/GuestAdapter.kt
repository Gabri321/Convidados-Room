package com.gabrielhernandes.convidados.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gabrielhernandes.convidados.R
import com.gabrielhernandes.convidados.service.data.GuestModel
import com.gabrielhernandes.convidados.view.GuestListener
import com.gabrielhernandes.convidados.view.viewholder.GuestViewHolder

class GuestAdapter : RecyclerView.Adapter<GuestViewHolder>() {

    private var mList: List<GuestModel> = arrayListOf()

    private lateinit var mListener: GuestListener

    companion object {
        var createCount: Int = 0
        var bindCount: Int = 0

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        var item = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_guests, parent, false)

        createCount++

        return GuestViewHolder(item, mListener)
    }

    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        holder.bind(mList[position])
        bindCount++
    }

    override fun getItemCount(): Int = mList.size

    fun updateGuests(list: List<GuestModel>) {

        mList = list
        notifyDataSetChanged()

    }

    fun attachListener(listener: GuestListener) {
        mListener = listener
    }


}