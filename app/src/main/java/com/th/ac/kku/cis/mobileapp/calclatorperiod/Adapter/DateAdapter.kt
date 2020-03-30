package com.th.ac.kku.cis.mobileapp.calclatorperiod.Adapter

import android.content.Context
import android.widget.ArrayAdapter


public class DateAdapter (val mCtx: Context,
                          var resource:Int,
                          var items:List<modelsave>): ArrayAdapter<modelsave>(mCtx,resource,items) {


}