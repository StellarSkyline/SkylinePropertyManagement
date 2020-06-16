package com.example.skylinepropertymanagement.data.repo

import com.example.skylinepropertymanagement.data.model.User

class AuthRepo {

    //Sample test
    fun login(email:String, password:String, type:Boolean):User {
        var user = User(email = email, password = password, type = type)

        return user

    }
}