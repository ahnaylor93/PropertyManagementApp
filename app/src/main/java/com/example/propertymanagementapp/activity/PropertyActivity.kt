package com.example.propertymanagementapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.propertymanagementapp.adapter.PropertyTabAdapter
import com.example.propertymanagementapp.databinding.ActivityPropertyBinding
import com.example.propertymanagementapp.fragment.property.AddPropertyFragment
import com.example.propertymanagementapp.fragment.property.ViewPropertyFragment

class PropertyActivity : AppCompatActivity() {

    lateinit var binding: ActivityPropertyBinding
    lateinit var adapter: PropertyTabAdapter
    lateinit var addPropertyFragment: AddPropertyFragment
    lateinit var viewPropertyFragment: ViewPropertyFragment
    val fragments = ArrayList<Fragment>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPropertyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        addPropertyFragment = AddPropertyFragment()
        viewPropertyFragment = ViewPropertyFragment()


        fragments.add(viewPropertyFragment)
        fragments.add(addPropertyFragment)

        adapter = PropertyTabAdapter(fragments, supportFragmentManager)

        binding.viewPager.adapter = adapter

        binding.tabs.setupWithViewPager(binding.viewPager)

        //binding.tabs.getTabAt()
    }
}