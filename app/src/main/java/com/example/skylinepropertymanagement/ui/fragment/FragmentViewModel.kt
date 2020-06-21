package com.example.skylinepropertymanagement.ui.fragment

import android.graphics.Bitmap
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.skylinepropertymanagement.app.Jump
import com.example.skylinepropertymanagement.data.model.Document
import com.example.skylinepropertymanagement.data.repo.FragmentRepo

class FragmentViewModel:ViewModel() {
    val repo = FragmentRepo()
//    var photoList:ArrayList<Bitmap> = ArrayList()
//    val photoList by lazy {MutableLiveData<ArrayList<Bitmap>>()}

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

    fun documentCameraPressed(view:View) {
        Jump.JUMP_TRIGGER.value = false
    }



}