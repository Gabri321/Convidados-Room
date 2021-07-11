package com.gabrielhernandes.convidados.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gabrielhernandes.convidados.R
import com.gabrielhernandes.convidados.service.constants.GuestConstants
import com.gabrielhernandes.convidados.viewmodel.GuestsFormViewModel
import kotlinx.android.synthetic.main.activity_guests_form.*

class GuestsFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mViewModel: GuestsFormViewModel
    private var mGuestId: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_guests_form)

        mViewModel = ViewModelProvider(this).get(GuestsFormViewModel::class.java)

        setListeners()
        observe()
        loadData()
        radio_present.isChecked = true


    }

    override fun onClick(v: View) {
        var id = v.id

        if (id == R.id.button_save) {

            var name = edit_name.text.toString()

            var present = radio_present.isChecked

            mViewModel.save(mGuestId, name, present)


        }
    }

    private fun setListeners() {
        button_save.setOnClickListener(this)
    }

    private fun observe() {
        mViewModel.saveGuest.observe(this, Observer {

            if (it) {
                Toast.makeText(applicationContext, "Deu certo", Toast.LENGTH_LONG).show()
            } else {

                Toast.makeText(applicationContext, "Deu ruim", Toast.LENGTH_LONG).show()


            }
            finish()
        })

        mViewModel.guest.observe(this, Observer {
            edit_name.setText(it.name)
            if (it.present) {
                radio_present.isChecked = true
            } else {
                radio_absent.isChecked = true
            }
        })
    }


    private fun loadData() {
        val bundle = intent.extras

        if (bundle != null) {
            mGuestId = bundle.getInt(GuestConstants.ID)

            mViewModel.load(mGuestId)
        }
    }


}
