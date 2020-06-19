package com.example.skylinepropertymanagement.data.repo

import com.example.skylinepropertymanagement.app.App
import com.example.skylinepropertymanagement.app.log
import com.example.skylinepropertymanagement.data.SessionManager
import com.example.skylinepropertymanagement.data.model.PropertyResponse
import com.example.skylinepropertymanagement.data.network.ApiClient
import com.example.skylinepropertymanagement.ui.fragment.FragmentViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FragmentRepo {
    val sm = SessionManager()


    fun getProperties():PropertyResponse {
        var response = PropertyResponse()
        //change userID to sm.getUserId()
        //change userType to sm.getUserType()
        var request = ApiClient.invoke().properties(userId ="3", userType = "landlord")

        request.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                var response = it
                App.instance.log(it.toString())

            },{
                App.instance.log(it.toString())

            })

        return response

    }


}