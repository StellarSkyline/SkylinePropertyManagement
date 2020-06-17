package com.example.skylinepropertymanagement.data.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.skylinepropertymanagement.app.App
import com.example.skylinepropertymanagement.app.log
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
                App.instance.log(it.toString())

            },{
                App.instance.log(it.toString())
            })




    }

    //sample Register
    fun register(user:MutableLiveData<User>) {
        var request = ApiClient.invoke().register(email = user.value!!.email, landEmail = user.value!!.email,password = user.value!!.password, account = user.value!!.type)

        request.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("STLog", it)

            }, {
                Log.d("STLog", it.toString() + "g")
            })
    }
}