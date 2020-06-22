package com.example.skylinepropertymanagement.ui.fragment

import android.graphics.Bitmap
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.skylinepropertymanagement.app.Jump
import com.example.skylinepropertymanagement.data.model.Document
import com.example.skylinepropertymanagement.data.model.PropertyAdd
import com.example.skylinepropertymanagement.data.repo.FragmentRepo

class FragmentViewModel:ViewModel() {
    val repo = FragmentRepo()

    val photoList by lazy {
        val x = MutableLiveData<ArrayList<Bitmap>>()
        x.value = ArrayList()
        x
    }

    val inputVal by lazy {MutableLiveData<String>()}

    val documentData by lazy {
        val x = MutableLiveData<Document>()
        x.value = Document()
        x
    }

    val propertyAdd by lazy {
        val x = MutableLiveData<PropertyAdd>()
        x.value = PropertyAdd()
        x
    }

    val checkJump by lazy { MutableLiveData<Boolean>() }


    fun onButtonDocument(view:View) {

        if(documentData.value?.name.isNullOrEmpty()){
            inputVal.value = "name"
            return
        } else if(documentData.value?.type.isNullOrEmpty()) {
            inputVal.value = "type"
            return
        }

        //call repo here
        documentData.value?.image =photoList.value.toString()
        repo.saveDocument(documentData)
        inputVal.value = "success"
    }

    fun documentCameraPressed(view:View) {
        Jump.JUMP_TRIGGER.value = false
    }

    fun onAddProperties(view:View) {
        checkJump.value = true
    }

    fun saveProperty(view:View) {
        repo.addProperty(propertyAdd)
    }


}