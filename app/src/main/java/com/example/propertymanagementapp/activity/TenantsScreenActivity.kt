package com.example.propertymanagementapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.propertymanagementapp.R
import com.example.propertymanagementapp.adapter.TenantsTabAdapter
import com.example.propertymanagementapp.databinding.ActivityPropertyBinding
import com.example.propertymanagementapp.databinding.ActivityTenantsScreenBinding
import com.example.propertymanagementapp.fragment.tenants.AddTenantFragments
import com.example.propertymanagementapp.fragment.tenants.ContactTenantsFragment

class TenantsScreenActivity : AppCompatActivity() {
    lateinit var binding:ActivityTenantsScreenBinding
    lateinit var addTenantFragment: AddTenantFragments
    lateinit var contactTenantsFragment: ContactTenantsFragment
    lateinit var tabAdapter: TenantsTabAdapter
    val fragments = ArrayList<Fragment>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityTenantsScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addTenantFragment = AddTenantFragments()
        contactTenantsFragment = ContactTenantsFragment()

        fragments.add(addTenantFragment)
        fragments.add(contactTenantsFragment)

        tabAdapter= TenantsTabAdapter(fragments,supportFragmentManager)

        binding.viewPager.adapter = tabAdapter

        binding.tabs.setupWithViewPager(binding.viewPager)
    }
}