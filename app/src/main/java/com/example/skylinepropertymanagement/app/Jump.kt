package com.example.skylinepropertymanagement.app

import androidx.lifecycle.MutableLiveData
import com.example.skylinepropertymanagement.data.model.Property

object Jump {
    val JUMP_TRIGGER by lazy{MutableLiveData<Boolean>()}
    val DELETE_PROP_TRIGGER by lazy {MutableLiveData<String>()}
    val DELETE_MEETING_TRIGGER by lazy{MutableLiveData<String>()}
}