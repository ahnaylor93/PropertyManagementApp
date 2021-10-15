package com.example.propertymanagementapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.propertymanagementapp.fragment.tenants.AddTenantFragments

class TenantsTabAdapter (val fragments: ArrayList<Fragment>, fragmentManager: FragmentManager):FragmentStatePagerAdapter(fragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){
    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0->return "Add Tenant"
            1->return "Contact Tenant"
        }
        return super.getPageTitle(position)
    }

}