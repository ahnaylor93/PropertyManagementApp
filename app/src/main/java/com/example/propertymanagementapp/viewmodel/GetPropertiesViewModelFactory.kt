package com.example.propertymanagementapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.propertymanagementapp.api.ApiService

class GetPropertiesViewModelFactory(val apiService: ApiService): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GetPropertiesViewModel(apiService) as T
    }
}
class GetTenantsViewModelFactory(val apiService: ApiService): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GetTenantsViewModel(apiService) as T
    }
}