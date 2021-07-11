package com.gabrielhernandes.convidados.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gabrielhernandes.convidados.R
import com.gabrielhernandes.convidados.service.constants.GuestConstants
import com.gabrielhernandes.convidados.view.adapter.GuestAdapter
import com.gabrielhernandes.convidados.viewmodel.GuestsViewModel

class PresentFragment : Fragment() {


    private var mAdapter = GuestAdapter()

    private lateinit var mViewModel: GuestsViewModel

    private lateinit var mListener: GuestListener
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewModel =
            ViewModelProvider(this).get(GuestsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_present, container, false)
        var recyclerview = root.findViewById<RecyclerView>(R.id.recycler_present)

        recyclerview.layoutManager = LinearLayoutManager(context)

        recyclerview.adapter = mAdapter

        observe()

        mListener = object : GuestListener {

            override fun onCreate(id: Int) {
                val intent = Intent(context, GuestsFormActivity::class.java)

                var bundle = Bundle()

                bundle.putInt(GuestConstants.ID, id)

                intent.putExtras(bundle)

                startActivity(intent)
            }

            override fun onDelete(id: Int) {
                mViewModel.delete(id)
                mViewModel.load(GuestConstants.FILTER.PRESENT)

            }
        }



        return root
    }


    override fun onResume() {
        super.onResume()
        mAdapter.attachListener(mListener)
        mViewModel.load(GuestConstants.FILTER.PRESENT)
    }

    private fun observe() {
        mViewModel.guestList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateGuests(it)
        })
    }
}