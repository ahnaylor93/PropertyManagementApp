package com.example.propertymanagementapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.propertymanagementapp.R
import com.example.propertymanagementapp.adapter.TenantsTabAdapter
import com.example.propertymanagementapp.api.response.Property
import com.example.propertymanagementapp.databinding.ActivityPropertyBinding
import com.example.propertymanagementapp.databinding.ActivityTenantsScreenBinding
import com.example.propertymanagementapp.databinding.FragmentSelectPropertyBinding
import com.example.propertymanagementapp.fragment.tenants.AddTenantFragments
import com.example.propertymanagementapp.fragment.tenants.ContactTenantsFragment
import com.example.propertymanagementapp.fragment.tenants.SelectPropertyFragment

class TenantsScreenActivity : AppCompatActivity() {
    lateinit var binding:ActivityTenantsScreenBinding
    lateinit var addTenantFragment: AddTenantFragments
    lateinit var contactTenantsFragment: ContactTenantsFragment
    lateinit var tabAdapter: TenantsTabAdapter
    lateinit var selectPropertyDialog:SelectPropertyFragment

    val fragments = ArrayList<Fragment>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityTenantsScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tv=findViewById(R.id.tv_selected_property)

        addTenantFragment = AddTenantFragments()
        contactTenantsFragment = ContactTenantsFragment()

        fragments.add(addTenantFragment)
        fragments.add(contactTenantsFragment)

        tabAdapter= TenantsTabAdapter(fragments,supportFragmentManager)

        binding.viewPager.adapter = tabAdapter

        binding.tabs.setupWithViewPager(binding.viewPager)

        selectPropertyDialog = SelectPropertyFragment()

        selectPropertyDialog.show(supportFragmentManager,"selectPropertyDialog")

        //binding.tvSelectedProperty.setText(selectedProperty?.address)

    }
    companion object{
        var selectedProperty: Property? = null
        lateinit var tv:TextView
        fun updateSelected(p:Property){
            selectedProperty = p
            tv.setText("${p.address}")
        }
    }
}