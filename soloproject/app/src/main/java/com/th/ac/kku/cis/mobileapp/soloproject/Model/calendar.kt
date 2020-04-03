package com.th.ac.kku.cis.mobileapp.soloproject.Model

class calendar {
    companion object Factory {
        fun create(): calendar = calendar()
    }

    var id: String? = null
    var nameevent: String? = null
    var add_location: String? = null
    var edit_detail: String? = null
    var dateset: String? = null
}