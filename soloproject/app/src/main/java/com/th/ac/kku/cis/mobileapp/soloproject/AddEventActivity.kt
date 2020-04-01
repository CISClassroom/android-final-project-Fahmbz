package com.th.ac.kku.cis.mobileapp.soloproject

import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add_event.*

class calendar {
    companion object Factory {
        fun create(): calendar = calendar()
    }
    var id:String?=null
    var nameevent:String?=null
    var add_location:String?=null
    var edit_detail :String?=null
    var dateset :String?=null
}

class AddEventActivity : AppCompatActivity() {
    private val PERMISSION_CODE = 1000;

    var adddt:calendar = calendar.create()

    lateinit var mDB: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_event)

        mDB = FirebaseDatabase.getInstance().reference

        button_save_event!!.setOnClickListener {
                var newData: calendar = calendar.create()

                var obj = mDB.child("calendar").push()
                newData.nameevent = name_event.text.toString()
                newData.add_location = add_location.text.toString()
                newData.dateset = dateset.text.toString()
                newData.edit_detail = edit_detail.text.toString()

                newData.id = obj.key
                obj.setValue(newData)
                Toast.makeText(applicationContext, "เพิ่มเรียบร้อยแล้ว", Toast.LENGTH_SHORT)
                    .show()
                var i = Intent(this, DetailEventCalendar::class.java)
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(i)
            }
        }

    }
