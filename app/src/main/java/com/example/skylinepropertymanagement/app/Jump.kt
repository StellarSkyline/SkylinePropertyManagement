package com.example.skylinepropertymanagement.app

import androidx.lifecycle.MutableLiveData

class Jump {
    companion object {
        val JUMP_TRIGGER by lazy{MutableLiveData<Boolean>()}
    }
}