package com.example.propertymanagementapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.propertymanagementapp.fragment.property.AddPropertyFragment
import com.example.propertymanagementapp.fragment.property.ViewPropertyFragment

class PropertyTabAdapter(val fragments: ArrayList<Fragment>, fm: FragmentManager): FragmentStatePagerAdapter(fm,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getCount() = fragments.size

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> return  "View Properties"
            1 -> return "Add Property"
        }
        return super.getPageTitle(position)
    }

}