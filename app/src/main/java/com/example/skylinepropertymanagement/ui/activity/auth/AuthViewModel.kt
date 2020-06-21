package com.example.skylinepropertymanagement.ui.activity.auth

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
    val user by lazy{
        val x = MutableLiveData<User>()
        x.value = User()
        x
    }



    fun loginButtonClicked(view: View) {
        if(user.value?.email.isNullOrEmpty()) {
            loginResult.value = "email"
            return
        } else if(user.value?.password.isNullOrEmpty()) {
            loginResult.value = "password"
            return
        }

        repo.login(user)
        loginResult.value = "success"

    }

    fun switchToggled(view:View){
        typeSwitch.value = typeSwitch.value != true
        if(typeSwitch.value == true) {
            user.value?.type = "landlord"
        } else {user.value?.type = "tennant"}

    }

    fun onRegisterButtonClicked(view: View) {
        when {
            user.value?.name.isNullOrEmpty() -> {
                loginResult.value = "name"
                return
            }
            user.value?.email.isNullOrEmpty() -> {
                loginResult.value = "email"
                return
            }
            user.value?.mobile.isNullOrEmpty() -> {
                loginResult.value = "mobile"
                return
            }
            user.value?.password.isNullOrEmpty() -> {
                loginResult.value = "password"
                return
            }
        }
        repo.register(user)
        loginResult.value = "success"
    }

}