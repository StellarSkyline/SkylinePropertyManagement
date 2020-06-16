package com.example.skylinepropertymanagement.ui.activity.auth

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.skylinepropertymanagement.app.App
import com.example.skylinepropertymanagement.app.log
import com.example.skylinepropertymanagement.data.model.User
import com.example.skylinepropertymanagement.data.repo.AuthRepo

class AuthViewModel:ViewModel() {
    var name:String? = null
    var email:String? = null
    var mobile:String? = null
    var password:String? = null
    var type:Boolean? = true
    var repo = AuthRepo()
    val loginResult by lazy {MutableLiveData<String>()}
    val typeSwitch by lazy {MutableLiveData<Boolean>()}

    fun loginButtonClicked(view: View) {
        if(email.isNullOrEmpty()) {
            loginResult.value = "email"
            return
        } else if(password.isNullOrEmpty()) {
            loginResult.value = "password"
            return
        }
        repo.login(email!!, password!!, type!!)
        App.instance.log(repo.login(email!!, password!!, type!!).toString())
        loginResult.value = "success"

    }

    fun switchToggled(view:View){
        typeSwitch.value = typeSwitch.value != true
        type = typeSwitch.value

    }

}