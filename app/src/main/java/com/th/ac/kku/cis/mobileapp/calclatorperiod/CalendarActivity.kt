package com.th.ac.kku.cis.mobileapp.calclatorperiod

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_about.*
import kotlinx.android.synthetic.main.activity_calendar.*
import java.util.*


class CalendarActivity : AppCompatActivity () {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        button_add.setOnClickListener {
            var i = Intent(this,AddEventActivity::class.java)
            i.setFlags((Intent.FLAG_ACTIVITY_CLEAR_TOP))
            startActivity(i)
        }
        button_about.setOnClickListener {
            var i = Intent(this,AboutActivity::class.java)
            i.setFlags((Intent.FLAG_ACTIVITY_CLEAR_TOP))
            startActivity(i)
        }
    }
}