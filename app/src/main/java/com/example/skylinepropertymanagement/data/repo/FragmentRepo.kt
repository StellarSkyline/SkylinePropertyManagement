package com.example.skylinepropertymanagement.data.repo

import android.content.SharedPreferences
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
import javax.inject.Inject
import javax.inject.Named

class FragmentRepo {
    @Inject
    @field:Named("user")
    lateinit var sm:SessionManager

    @Inject
    @field:Named("api")
    lateinit var request:ApiClient

    val db = DB.createDatabase(App.instance)

    val propertyData by lazy{ MutableLiveData<List<Property>>()}
    val meetingData by lazy{MutableLiveData<List<Meeting>>()}
    val tennant by lazy{MutableLiveData<List<Tennant>>()}



    fun getProperties() {
        //change userID to sm.getUserId()
        //change userType to sm.getUserType()
        //var request = ApiClient.invoke().getAllProperties()

        request.getAllProperties().subscribeOn(Schedulers.io())
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
        request.addProperty(address = property.value?.address!!,
            city = property.value?.city!!,
            state = property.value?.state!!,
            country = property.value?.country!!,
            pro_status =property.value?.pro_status!!,
            purchase_price =property.value?.purchase_price!!,
            mortage_info = property.value?.mortage_info!!,
            userid = sm.getUserId(),
            usertype = sm.getType(),
            latitude = property.value?.Latitude!!,
            longitude = property.value?.longitude!!).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                App.instance.toast(it.msg.toString())


            },{
                App.instance.log(it.toString())
            })


    }

    fun getPropertyList() {
        request.properties(userId = sm.getUserId(), userType = sm.getType())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                propertyData.value = it.Property
                App.instance.log(it.toString())

            },{
                App.instance.log(it.toString())
            })
    }

    fun deleteProperty(propertyid:String) {
        request.removeProperty(propertyid = propertyid)
            .subscribeOn(Schedulers.io())
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

        request.addTennant(name = user.value!!.name,email=user.value!!.email, address = address,mobile = user.value!!.mobile, landlordid = sm.getUserId(),propertyid = propertyid)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                App.instance.log(it.toString())

                when(it.toString()) {
                    "successfully" -> {App.instance.toast("Successfully Added Tennant")}
                    "Email" -> {App.instance.toast("Property already rented out")}
                }
            },{
                App.instance.log(it.toString())
            })

    }

    fun getTennant(){
        request.getTennant(sm.getUserId())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                App.instance.log(it.toString())

                tennant.value = it.Tenants

            },{
                App.instance.log(it.toString())
            })
    }



}