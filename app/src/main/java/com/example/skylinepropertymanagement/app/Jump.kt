package com.example.skylinepropertymanagement.app

import androidx.lifecycle.MutableLiveData
import com.example.skylinepropertymanagement.data.model.Property

object Jump {
    val JUMP_TRIGGER by lazy{MutableLiveData<Boolean>()}
}