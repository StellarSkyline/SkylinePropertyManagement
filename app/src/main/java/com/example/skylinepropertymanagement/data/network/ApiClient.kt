package com.example.skylinepropertymanagement.data.network

import com.example.skylinepropertymanagement.app.Config
import com.example.skylinepropertymanagement.data.model.LoginResponse
import com.example.skylinepropertymanagement.data.model.PropertyResponse
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import io.reactivex.Observable
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {

    @GET("pro_mgt_reg.php")
    fun register(@Query("email")email:String, @Query("landlord_email")landEmail:String, @Query("password")password:String, @Query("account_for")account:String): Observable<Any>

    @GET("pro_mgt_login.php")
    fun login(@Query("email")email:String, @Query("password")password:String):Observable<LoginResponse>

    @GET("property.php")
    fun properties(@Query("userid")userId:String, @Query("usertype")userType:String):Observable<PropertyResponse>

    @GET("pro_mgt_property_all.php ")
    fun getAllProperties():Observable<PropertyResponse>

    companion object {
        operator fun invoke():ApiClient {
            return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                .baseUrl(Config.BASE_URL)
                .build()
                .create(ApiClient::class.java)
        }
    }
}
