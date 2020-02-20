package com.ricardocenteno.laundry.viewmodels

import androidx.lifecycle.ViewModel
import com.ricardocenteno.laundry.models.Washer

class LoginViewModel : ViewModel() {
    val washer = Washer(
        1,
        "Washer 1",
        Washer.Status("Busy")
    )
}
