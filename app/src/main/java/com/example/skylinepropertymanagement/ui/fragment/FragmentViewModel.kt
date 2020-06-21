package com.example.skylinepropertymanagement.ui.fragment

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.skylinepropertymanagement.data.model.Document
import com.example.skylinepropertymanagement.data.repo.FragmentRepo

class FragmentViewModel:ViewModel() {
    val repo = FragmentRepo()
    val inputVal by lazy {MutableLiveData<String>()}

    val documentData by lazy {
        val x = MutableLiveData<Document>()
        x.value = Document()
        x
    }

    fun onButtonDocument(view:View) {

        if(documentData.value?.name.isNullOrEmpty()){
            inputVal.value = "name"
            return
        } else if(documentData.value?.type.isNullOrEmpty()) {
            inputVal.value = "type"
            return
        }

        //call repo here
        repo.saveDocument(documentData)
        inputVal.value = "success"
    }



}