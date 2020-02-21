package com.ricardocenteno.laundry.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ricardocenteno.laundry.R

class LoginViewModel : ViewModel() {

    val navigate = MutableLiveData<Boolean>()
    var email: ObservableField<String> = ObservableField()
    var password: ObservableField<String> = ObservableField()
    var emailError: ObservableField<Int> = ObservableField()
    var passwordError: ObservableField<Int> = ObservableField()

    fun onClickLogin() {
        if (email.get().isNullOrEmpty()) {
            emailError.set(R.string.error_field_required)
        } else {
            emailError.set(null)
            email.get()?.let {
                if (!isEmailValid(it)) {
                    emailError.set(R.string.error_invalid_email)
                }
            }
        }

        if (password.get().isNullOrEmpty()) {
            passwordError.set(R.string.error_field_required)
        } else {
            passwordError.set(null)
        }

        if (emailError.get() == null && passwordError.get() == null) {
            navigate.postValue(true)
        }
    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
