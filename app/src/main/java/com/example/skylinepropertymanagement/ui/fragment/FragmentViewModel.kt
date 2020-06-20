package com.example.skylinepropertymanagement.ui.fragment

import android.view.View
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.skylinepropertymanagement.app.App
import com.example.skylinepropertymanagement.app.log
import com.example.skylinepropertymanagement.data.model.Document
import com.example.skylinepropertymanagement.data.model.Property
import com.example.skylinepropertymanagement.data.model.User
import com.example.skylinepropertymanagement.data.repo.FragmentRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FragmentViewModel:ViewModel() {
    val repo = FragmentRepo()

    val documentData by lazy {
        val x = MutableLiveData<Document>()
        x.value = Document()
        x
    }




    fun loadProperties() {
        repo.getProperties()
    }



}