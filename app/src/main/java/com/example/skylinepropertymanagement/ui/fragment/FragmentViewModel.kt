package com.example.skylinepropertymanagement.ui.fragment

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skylinepropertymanagement.app.App
import com.example.skylinepropertymanagement.app.log
import com.example.skylinepropertymanagement.data.model.Property
import com.example.skylinepropertymanagement.data.repo.FragmentRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentViewModel:ViewModel() {
    val repo = FragmentRepo()

    val propertyData by lazy {MutableLiveData<List<Property>>()}



    fun loadProperties() {
        propertyData.value = repo.getProperties().Property


    }



}