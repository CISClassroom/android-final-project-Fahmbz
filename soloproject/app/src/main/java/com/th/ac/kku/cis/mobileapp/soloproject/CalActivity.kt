package com.th.ac.kku.cis.mobileapp.soloproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.th.ac.kku.cis.mobileapp.soloproject.Adapter.DateAdapter
import com.th.ac.kku.cis.mobileapp.soloproject.Model.calendar
import kotlinx.android.synthetic.main.activity_about.*
import kotlinx.android.synthetic.main.activity_calendar.*


class CalActivity : AppCompatActivity() {
    lateinit var auth : FirebaseAuth
    lateinit var listView: ListView
    lateinit var refdb : DatabaseReference
    lateinit var calendarView: CalendarView
    lateinit var items : MutableList<calendar>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        calendarView = findViewById(R.id.calendarView)
        listView = findViewById(R.id.detailtime)//นำข้อมูลจาก firebase มาแสดงใน list

        bind()

    }
    private  fun bind(){
        items = mutableListOf()
        refdb = FirebaseDatabase.getInstance().getReference("calendar")//เรียกใช้ข้อมูลใน firebase
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
                    val adapter = DateAdapter(this@CalActivity,R.layout.activity_detail_calendar ,items)
                    listView.adapter = adapter
                }
            }
        })
        listView.setOnItemClickListener{ parent, view, position, id ->
            var i = Intent(this,CalActivity::class.java)
            i.putExtra("id",items[position].id)
            i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(i)
           }

        button_about.setOnClickListener {
            var i = Intent(this, aboutActivity::class.java)
            i.setFlags((Intent.FLAG_ACTIVITY_CLEAR_TOP))
            startActivity(i)
        }
    }
}