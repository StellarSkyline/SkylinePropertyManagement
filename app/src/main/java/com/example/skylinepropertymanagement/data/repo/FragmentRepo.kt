package com.example.skylinepropertymanagement.data.repo

import androidx.lifecycle.MutableLiveData
import com.example.skylinepropertymanagement.app.App
import com.example.skylinepropertymanagement.app.Jump
import com.example.skylinepropertymanagement.app.log
import com.example.skylinepropertymanagement.data.SessionManager
import com.example.skylinepropertymanagement.data.database.DB
import com.example.skylinepropertymanagement.data.database.entity.SavedDocuments
import com.example.skylinepropertymanagement.data.model.Document
import com.example.skylinepropertymanagement.data.model.Property
import com.example.skylinepropertymanagement.data.network.ApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FragmentRepo {
    val sm = SessionManager()
    val db = DB.createDatabase(App.instance)

    val propertyData by lazy{ MutableLiveData<List<Property>>()}



    fun getProperties() {
        //change userID to sm.getUserId()
        //change userType to sm.getUserType()
        var request = ApiClient.invoke().getAllProperties()

        request.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                propertyData.value = it.Property

                App.instance.log(it.toString())

            },{
                App.instance.log(it.toString())

            })


    }

    fun saveDocument(document:MutableLiveData<Document>) {
        db.Dao().addDocument(SavedDocuments(name = document.value!!.name,type= document.value!!.type, image = document.value!!.image))


    }


}