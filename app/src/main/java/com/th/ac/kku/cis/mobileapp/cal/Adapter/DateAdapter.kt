package com.th.ac.kku.cis.mobileapp.calclatorperiod.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.th.ac.kku.cis.mobileapp.calclatorperiod.R
import com.th.ac.kku.cis.mobileapp.calclatorperiod.calendar

public class DateAdapter (val mCtx: Context,
                         var resource:Int,
                          var items:List<calendar>): ArrayAdapter<calendar>(mCtx,resource,items) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout: LayoutInflater = LayoutInflater.from(mCtx)

        val v: View = layout.inflate(resource, null)
        val name: TextView = v.findViewById(R.id.name_event)
        val location: TextView = v.findViewById(R.id.add_location)
        val date: TextView = v.findViewById(R.id.dateset)
        val detail: TextView = v.findViewById(R.id.edit_detail)

        val AddModel: calendar = items[position]

        name.text = AddModel.nameevent
        location.text = AddModel.add_location
        date.text = AddModel.dateset
        detail.text = AddModel.edit_detail

        return v
    }
}
