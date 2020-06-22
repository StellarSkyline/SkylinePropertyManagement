package com.example.skylinepropertymanagement.data.network

import com.example.skylinepropertymanagement.app.Config
import com.example.skylinepropertymanagement.data.model.LoginResponse
import com.example.skylinepropertymanagement.data.model.PropertyAddResponse
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

    @GET("pro_mgt_add_pro.php")
    fun addProperty(@Query("address")address:String, @Query("city")city:String,
                    @Query("state")state:String,
                    @Query("country")country:String,
                    @Query("pro_status")pro_status:String,
                    @Query("purchase_price")purchase_price:String,
                    @Query("mortage_info")mortage_info:String,
                    @Query("userid")userid:String,
                    @Query("usertype")usertype:String,
                    @Query("latitude")latitude:String,
                    @Query("longitude")longitude:String
    ):Observable<PropertyAddResponse>



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
