package com.example.skylinepropertymanagement.data.repo

import androidx.lifecycle.MutableLiveData
import com.example.skylinepropertymanagement.app.App
import com.example.skylinepropertymanagement.app.Jump
import com.example.skylinepropertymanagement.app.log
import com.example.skylinepropertymanagement.app.toast
import com.example.skylinepropertymanagement.data.SessionManager
import com.example.skylinepropertymanagement.data.database.DB
import com.example.skylinepropertymanagement.data.database.entity.MeetingTable
import com.example.skylinepropertymanagement.data.database.entity.SavedDocuments
import com.example.skylinepropertymanagement.data.model.*
import com.example.skylinepropertymanagement.data.network.ApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FragmentRepo {
    val sm = SessionManager()
    val db = DB.createDatabase(App.instance)

    val propertyData by lazy{ MutableLiveData<List<Property>>()}
    val meetingData by lazy{MutableLiveData<List<Meeting>>()}



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

    fun addProperty(property:MutableLiveData<PropertyAdd>) {
        var request = ApiClient.invoke().addProperty(address = property.value?.address!!,
            city = property.value?.city!!,
            state = property.value?.state!!,
            country = property.value?.country!!,
            pro_status =property.value?.pro_status!!,
            purchase_price =property.value?.purchase_price!!,
            mortage_info = property.value?.mortage_info!!,
            userid = sm.getUserId(),
            usertype = sm.getType(),
            latitude = property.value?.Latitude!!,
            longitude = property.value?.longitude!!)

        request.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                App.instance.toast(it.msg.toString())


            },{
                App.instance.log(it.toString())
            })
    }

    fun getPropertyList() {
        var request = ApiClient.invoke().properties(userId = sm.getUserId(), userType = sm.getType())
        request.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                propertyData.value = it.Property
                App.instance.log(it.toString())

            },{
                App.instance.log(it.toString())
            })
    }

    fun deleteProperty(propertyid:String) {
        var request = ApiClient.invoke().removeProperty(propertyid = propertyid)

        request.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                App.instance.toast(it.toString())

            },{
                App.instance.log(it.toString())
            })

    }

    fun getMeeting():List<Meeting>{
        return db.Dao().getMeeting()

    }

    fun addMeeting(meeting:MutableLiveData<Meeting>) {
        db.Dao().addMeeting(MeetingTable(name = meeting.value?.name!!, location = meeting.value?.location!!,time = meeting.value?.time!!,description = meeting.value?.description!!))
        meetingData.value = db.Dao().getMeeting()
        App.instance.toast("Meeting Saved")
    }

    fun deleteMeeting(id:String) {
        db.Dao().deleteMeeting(id)

    }


    fun addTennant(user:MutableLiveData<User>, propertyid:String, address:String) {

        val request = ApiClient.invoke().addTennant(name = user.value!!.name,email=user.value!!.email, address = address,mobile = user.value!!.mobile, landlordid = sm.getUserId(),propertyid = propertyid)

        request.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                //Handle Response
                App.instance.log(it.toString())
            },{
                App.instance.log(it.toString())
            })

    }




}