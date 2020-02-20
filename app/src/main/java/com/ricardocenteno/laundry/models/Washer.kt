package com.ricardocenteno.laundry.models

class Washer (
    var id : Int,
    var label : String,
    var status : Status
) {
    class Status (
        val description : String
    )
}



