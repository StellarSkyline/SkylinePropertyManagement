package com.example.skylinepropertymanagement.data.repo

import androidx.lifecycle.MutableLiveData
import com.example.skylinepropertymanagement.app.App
import com.example.skylinepropertymanagement.app.log
import com.example.skylinepropertymanagement.data.model.User

class AuthRepo {

    //Sample test
    fun login(user:MutableLiveData<User>){
        App.instance.log(user.value.toString())

    }
}