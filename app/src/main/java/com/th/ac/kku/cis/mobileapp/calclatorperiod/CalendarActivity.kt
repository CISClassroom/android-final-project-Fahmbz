package com.th.ac.kku.cis.mobileapp.calclatorperiod

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.CalendarView
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_calendar.*


class CalendarActivity : AppCompatActivity () {

    var calendar: CalendarView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        calendar = findViewById(R.id.calendarView)

        button_add.setOnClickListener {
            var i = Intent(this, AddEventActivity::class.java)
            i.setFlags((Intent.FLAG_ACTIVITY_CLEAR_TOP))
            startActivity(i)
        }
        button_about.setOnClickListener {
            var i = Intent(this, AboutActivity::class.java)
            i.setFlags((Intent.FLAG_ACTIVITY_CLEAR_TOP))
            startActivity(i)
        }
        button_detail.setOnClickListener { var i = Intent(this, DetailEventCalendar::class.java)
            i.setFlags((Intent.FLAG_ACTIVITY_CLEAR_TOP))
            startActivity(i) }
    }
}