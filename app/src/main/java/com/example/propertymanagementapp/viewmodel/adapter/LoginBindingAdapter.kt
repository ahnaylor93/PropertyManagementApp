package com.example.propertymanagementapp.viewmodel.adapter

import android.widget.EditText
import androidx.databinding.BindingAdapter

class LoginBindingAdapter {
    companion object{
        @JvmStatic
        @BindingAdapter("error_text")
        fun setErrorText(editText: EditText, errorText: String?){
            editText.setError(errorText)
        }
    }
}