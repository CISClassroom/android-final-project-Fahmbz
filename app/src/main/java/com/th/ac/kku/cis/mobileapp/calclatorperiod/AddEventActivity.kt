package com.th.ac.kku.cis.mobileapp.calclatorperiod

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

class AddEventActivity : AppCompatActivity() {
    var name_event : EditText? = null
    var add_location : EditText? = null
    var edit_detail : EditText? = null
    var button_save_event : Button? = null
    var dateset : EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_event)

        name_event = findViewById(R.id.name_event)
        add_location = findViewById(R.id.add_location)
        edit_detail = findViewById(R.id.edit_detail)
        dateset = findViewById(R.id.dateset)
        button_save_event = findViewById(R.id.button_save_event)

        button_save_event!!.setOnClickListener {
            intent = Intent(this, DetailEventCalendar::class.java)
            intent.putExtra("NAME",""+ name_event!!.getText().toString())
            intent.putExtra("LOCATION",""+ add_location!!.getText().toString())
            intent.putExtra("DETAIL",""+ edit_detail!!.getText().toString())
            intent.putExtra("DATE",""+dateset!!.getText().toString())
            startActivity(intent)


        }
    }
}