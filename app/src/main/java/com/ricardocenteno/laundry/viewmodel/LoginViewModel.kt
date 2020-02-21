package com.ricardocenteno.laundry.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.ricardocenteno.laundry.R

class LoginViewModel : ViewModel() {

    var email: ObservableField<String> = ObservableField()
    var password: ObservableField<String> = ObservableField()
    var emailError: ObservableField<Int> = ObservableField()
    var passwordError: ObservableField<Int> = ObservableField()

    fun onClickLogin() {
        Log.e("DEBUG", "On Click")
        var isValidLogin = true
        if (email.get().isNullOrEmpty()) {
            emailError.set(R.string.error_field_required)
            isValidLogin = false
            Log.e("DEBUG", "error email")
        } else {
            emailError.set(null)
            isValidLogin = true
            email.get()?.let {
                if (!isEmailValid(it)) {
                    isValidLogin = false
                    emailError.set(R.string.error_invalid_email)
                }
            }
        }

        if (password.get().isNullOrEmpty()) {
            passwordError.set(R.string.error_field_required)
            isValidLogin = false
            Log.e("DEBUG", "error passoword")
        } else {
            passwordError.set(null)
            isValidLogin = true
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
