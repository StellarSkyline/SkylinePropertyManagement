package com.example.skylinepropertymanagement.data.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.skylinepropertymanagement.app.App
import com.example.skylinepropertymanagement.app.Jump
import com.example.skylinepropertymanagement.app.log
import com.example.skylinepropertymanagement.app.toast
import com.example.skylinepropertymanagement.data.SessionManager
import com.example.skylinepropertymanagement.data.model.User
import com.example.skylinepropertymanagement.data.network.ApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AuthRepo {

    //Sample test
    fun login(user:MutableLiveData<User>){
        var request = ApiClient.invoke().login(email = user.value!!.email, password=user.value!!.password)
        request.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                when(it.msg.toString()) {
                    "success" -> {
                        //store in shared preference
                        SessionManager().addUser(useremail = it.useremail, userid = it.userid,userType = it.usertype,api = it.appapikey)
                        Jump.JUMP_TRIGGER.value = true
                        App.instance.log(it.toString())
                    }
                    "[Email is not register]" -> {
                        App.instance.toast("Email is not Registered")
                    }
                    "[try in next 5 mins]" -> {
                        App.instance.toast("Account has been locked, please try in the next 5 minutes")
                    }

                    "[2.0]" -> {
                        App.instance.toast("You have 2 tries left")
                    }
                    "[1.0]" -> {
                        App.instance.toast("You have 1 tries left")
                    }
                    "[0.0]" -> {
                        App.instance.toast("You have 0 tries left")
                    }

                }

            },{
                App.instance.toast("Account blocked, please try again in 5 minutes")
            })




    }

    //sample Register
    fun register(user:MutableLiveData<User>) {
        var request = ApiClient.invoke().register(email = user.value!!.email, landEmail = user.value!!.email,password = user.value!!.password, account = user.value!!.type)

        request.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                when(it.toString()) {
                    "successfully" -> {App.instance.toast("Registered Successfully")}
                    "Email" -> {App.instance.toast("User Already Registered")}
                }

            }, {
                Log.d("STLog", it.toString() + "g")
            })
    }
}