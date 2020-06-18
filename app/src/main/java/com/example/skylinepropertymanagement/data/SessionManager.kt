package com.example.skylinepropertymanagement.data

import android.content.Context
import com.example.skylinepropertymanagement.app.App
import com.example.skylinepropertymanagement.app.Config

class SessionManager() {

//    var sharePreference = App.instance.getSharedPreferences(Config.FILE_NAME,Context.MODE_PRIVATE)
    var editor = sharePreference.edit()

    fun addUser(userid:String, userType:String, useremail:String, api:String) {
        editor.putString("userId", userid)
        editor.putString("userType", userType)
        editor.putString("userEmail",useremail)
        editor.putString("ApiKey",api)
        editor.commit()
    }

    companion object {
        val sharePreference by lazy {
            App.instance.getSharedPreferences(Config.FILE_NAME,Context.MODE_PRIVATE)
        }
    }
}