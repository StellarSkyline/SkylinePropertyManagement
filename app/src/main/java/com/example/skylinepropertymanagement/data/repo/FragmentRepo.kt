package com.example.skylinepropertymanagement.data.repo

import androidx.lifecycle.MutableLiveData
import com.example.skylinepropertymanagement.app.App
import com.example.skylinepropertymanagement.app.Jump
import com.example.skylinepropertymanagement.app.log
import com.example.skylinepropertymanagement.data.SessionManager
import com.example.skylinepropertymanagement.data.model.Property
import com.example.skylinepropertymanagement.data.model.PropertyResponse
import com.example.skylinepropertymanagement.data.network.ApiClient
import com.example.skylinepropertymanagement.ui.fragment.FragmentViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class FragmentRepo {
    val sm = SessionManager()
    val propertyData by lazy{ MutableLiveData<List<Property>>()}



    fun getProperties() {
        //change userID to sm.getUserId()
        //change userType to sm.getUserType()
        var request = ApiClient.invoke().properties(userId ="3", userType = "landlord")

        request.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                propertyData.value = it.Property

                App.instance.log(it.toString())

            },{
                App.instance.log(it.toString())

            })


    }


}