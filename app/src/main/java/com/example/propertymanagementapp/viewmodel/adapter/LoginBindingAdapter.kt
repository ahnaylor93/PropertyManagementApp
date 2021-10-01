package com.example.propertymanagementapp.viewmodel.adapter

import android.graphics.drawable.Drawable
import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseMethod
import androidx.recyclerview.widget.RecyclerView
import com.example.propertymanagementapp.adapter.PropertyAdapter
import com.example.propertymanagementapp.api.response.Property
import com.squareup.picasso.Picasso
import java.lang.NumberFormatException

class LoginBindingAdapter {
    companion object{
        @JvmStatic
        @BindingAdapter("error_text")
        fun setErrorText(editText: EditText, errorText: String?){
            editText.setError(errorText)
        }

        @JvmStatic
        @BindingAdapter("properties")
        fun setProperties(recyclerView: RecyclerView, list: List<Property>?) {
            list?.let {
                recyclerView.adapter = PropertyAdapter(it)
            }
        }

        @JvmStatic
        @InverseMethod("convertDoubleToString")
        fun convertStringToDouble(str: String): Double {
            try {
                return str.toDouble()
            } catch (e: NumberFormatException) {
                return 0.0
            }
        }

        @JvmStatic
        fun convertDoubleToString(n: Double): String {
            return "$n"
        }

        @JvmStatic
        @BindingAdapter("remote_source", "place_holder", requireAll = false)
        fun getImgFromRemoteSource(
            imageView: ImageView,
            url: String?,
            placeholder: Drawable?
        ) {
            url?.let {
                if (placeholder == null) {
                    Picasso.get().load(url).into(imageView)
                } else {
                    try {
                        Picasso.get()
                            .load(url)
                            .placeholder(placeholder).into(imageView)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }

        }
    }
}