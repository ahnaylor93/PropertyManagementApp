package com.example.propertymanagementapp.fragment.property

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.propertymanagementapp.databinding.FragmentAddPropertyBinding

class AddPropertyFragment : Fragment() {
    lateinit var binding: FragmentAddPropertyBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        try {
            binding = FragmentAddPropertyBinding.inflate(layoutInflater, container, false)

            setHasOptionsMenu(true)
        } catch (e: Exception) {
            Log.d("FRAGMENT ERROR", e.message ?: "UNKNOWN")
            throw e
        }
        return binding.root
    }
}