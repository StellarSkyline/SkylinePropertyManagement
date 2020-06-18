package com.example.skylinepropertymanagement.ui.fragment

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.skylinepropertymanagement.app.App
import com.example.skylinepropertymanagement.app.log
import com.example.skylinepropertymanagement.data.repo.FragmentRepo

class FragmentViewModel:ViewModel() {
    val repo = FragmentRepo()
    var name:String = ""

    fun onButtonClicked(view: View) {

        App.instance.log(name)

    }

    fun loadProperties() {
        repo.getProperties()
    }



}