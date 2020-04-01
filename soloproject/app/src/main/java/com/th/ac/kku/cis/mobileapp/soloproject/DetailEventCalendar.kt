package com.th.ac.kku.cis.mobileapp.soloproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailEventCalendar : AppCompatActivity () {
    var Showevent: TextView? = null
    var Showlocation: TextView? = null
    var Showdetail: TextView? = null
    var Showcalendar: TextView? = null
    var button_back: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_calendar)

        Showevent = findViewById(R.id.Showevent)
        Showlocation = findViewById(R.id.Showlocation)
        Showdetail = findViewById(R.id.Showdetail)
        button_back = findViewById(R.id.button_back)

        var intent = getIntent()
        Showevent!!.setText("" + intent.getStringExtra("NAME"))
        Showlocation!!.setText("" + intent.getStringExtra("LOCATION"))
        Showdetail!!.setText("" + intent.getStringExtra("DETAIL"))

        val button_back: Button = findViewById(R.id.button_back)
        button_back.setOnClickListener {
            var i = Intent(this, CalendarActivity::class.java)
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(i)
        }
    }
}