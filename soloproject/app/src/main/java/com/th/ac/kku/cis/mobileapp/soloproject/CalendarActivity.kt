package com.th.ac.kku.cis.mobileapp.soloproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.th.ac.kku.cis.mobileapp.soloproject.Adapter.DateAdapter
import com.th.ac.kku.cis.mobileapp.soloproject.AddEventActivity
import com.th.ac.kku.cis.mobileapp.soloproject.calendar
import kotlinx.android.synthetic.main.activity_calendar.*

class CalendarActivity : AppCompatActivity () {
    lateinit var auth : FirebaseAuth
    lateinit var listView: ListView
    lateinit var refdb : DatabaseReference
    lateinit var items : MutableList<calendar>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        listView = findViewById(R.id.detaildate)//นำข้อมูลจาก fibase มาแสดงในlist
        items = mutableListOf()
        refdb = FirebaseDatabase.getInstance().getReference("calendar")//เรียกใช้ข้อมูลใน fibase
        refdb.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }
            override fun onDataChange(p0: DataSnapshot) {
                if(p0!!.exists()){
                    items.clear()
                    for (e in p0.children){
                        val rec = e.getValue(calendar::class.java)
                        items.add(rec!!)
                    }
                    val adapter = DateAdapter(this@CalendarActivity,R.layout.activity_detail_calendar ,items)
                    listView.adapter = adapter//เอาข้อมูลจาก fibase มาแสดงหน้า Home
                }
            }
        })


        auth = FirebaseAuth.getInstance()
        val name: TextView = findViewById(R.id.name_event)
        val location: TextView = findViewById(R.id.add_location)
        val date: TextView = findViewById(R.id.dateset)
        val detail: TextView = findViewById(R.id.edit_detail)
        name.text = auth.currentUser!!.displayName.toString()

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