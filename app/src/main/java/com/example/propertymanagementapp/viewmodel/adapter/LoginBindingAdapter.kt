package com.example.propertymanagementapp.viewmodel.adapter

import android.graphics.drawable.Drawable
import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseMethod
import androidx.recyclerview.widget.RecyclerView
import com.example.propertymanagementapp.activity.TenantsScreenActivity
import com.example.propertymanagementapp.adapter.PropertyAdapter
import com.example.propertymanagementapp.adapter.TenantAdapter
import com.example.propertymanagementapp.api.response.Property
import com.example.propertymanagementapp.api.response.Tenant
import com.example.propertymanagementapp.fragment.tenants.SelectPropertyFragment
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
        @BindingAdapter("properties","fragment")
        fun setProperties(recyclerView: RecyclerView,list: List<Property>?,fragment: SelectPropertyFragment) {
            list?.let {

                var propertyAdapter = PropertyAdapter(it)
                propertyAdapter.setOnPropertySelectListener { property ->
                    TenantsScreenActivity.updateSelected(property)
                    fragment.dismiss()
                }
                recyclerView.adapter = propertyAdapter
            }
        }

        @JvmStatic
        @BindingAdapter("properties")
        fun setProperties(recyclerView: RecyclerView, list: List<Property>?) {
            list?.let {

                var propertyAdapter = PropertyAdapter(it)
                recyclerView.adapter = propertyAdapter
            }
        }


        @JvmStatic
        @BindingAdapter("tenants")
        fun setTenants(recyclerView: RecyclerView, list: List<Tenant>?) {
            list?.let {

                var tenantAdapter = TenantAdapter(it)
                recyclerView.adapter = tenantAdapter
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
        fun convertDoubleToString(n: Double?): String {
            if(n==null){
                return "null"
            }
            return "$n"
        }

        @JvmStatic
        @BindingAdapter("remote_source", "place_holder", requireAll = false)
        fun getImgFromRemoteSource(
            imageView: ImageView,
            url: String?,
            placeholder: Drawable?
        ) {
            var url2=url
            if(url=="https://insidelatinamerica.net/wp-content/uploads/2018/01/noImg_2.jpg"){
                url2=null
            }
            url2?.let {
                if (placeholder == null) {
                    Picasso.get().load(url2).into(imageView)
                } else {
                    try {
                        Picasso.get()
                            .load(url2)
                            .placeholder(placeholder).into(imageView)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }

        }
    }
}