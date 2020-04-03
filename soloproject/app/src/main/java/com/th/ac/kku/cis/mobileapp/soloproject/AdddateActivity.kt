package com.th.ac.kku.cis.mobileapp.soloproject

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.th.ac.kku.cis.mobileapp.soloproject.Model.calendar
import kotlinx.android.synthetic.main.activity_about.*
import kotlinx.android.synthetic.main.activity_add_event.*

class AdddateActivity : AppCompatActivity() {

    lateinit var mDB: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {

        lateinit var mDB: DatabaseReference

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_event)
        mDB = FirebaseDatabase.getInstance().reference
        
        btadddate.setOnClickListener {
            var newData: calendar = calendar()
            val obj = mDB.child("calendar").push()
            newData.nameevent = name_event.text.toString()
            newData.add_location = add_location.text.toString()
            newData.dateset = dateset.text.toString()
            newData.edit_detail = edit_detail.text.toString()

            newData.id = obj.key
            obj.setValue(newData)
        }
        btcancel.setOnClickListener {
            var i = Intent(this, aboutActivity::class.java)
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(i)

        }
    }
}
