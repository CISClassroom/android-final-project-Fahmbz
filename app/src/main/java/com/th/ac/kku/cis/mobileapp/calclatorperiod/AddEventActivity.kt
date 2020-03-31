package com.th.ac.kku.cis.mobileapp.calclatorperiod

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
import com.th.ac.kku.cis.mobileapp.calclatorperiod.Model.AddModel
import kotlinx.android.synthetic.main.activity_add_event.*

class AddEventActivity : AppCompatActivity() {

//    val nameevent: EditText? = findViewById(R.id.name_event)
//    val add_location: EditText? = findViewById(R.id.add_location)
//    val edit_detail: EditText? = findViewById(R.id.edit_detail)
//    val button_save_event: Button? = findViewById(R.id.button_save_event)
//    val dateset: EditText? = findViewById(R.id.dateset)

    lateinit var mDB: DatabaseReference
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_event)

        mDB = FirebaseDatabase.getInstance().reference

        button_save_event!!.setOnClickListener {
            var newData: AddModel = AddModel.create()
            val obj = mDB.child("DTitem").push()
            newData.nameevent = name_event.text.toString()
            newData.add_location = add_location.text.toString()
            newData.edit_detail = edit_detail.text.toString()
            newData.dateset = dateset.text.toString()

            newData.id = obj.key
            obj.setValue(newData)
            Toast.makeText(applicationContext, "เพิ่มกิจกรรมเรียบร้อยแล้ว", Toast.LENGTH_SHORT)
                .show()
        }
          //  intent = Intent(this, DetailEventCalendar::class.java)
            //intent.putExtra("NAME", "" + name_event!!.getText().toString())
            //intent.putExtra("LOCATION", "" + add_location!!.getText().toString())
            //intent.putExtra("DETAIL", "" + edit_detail!!.getText().toString())
            //intent.putExtra("DATE", "" + dateset!!.getText().toString())
            //startActivity(intent)
        }
    }