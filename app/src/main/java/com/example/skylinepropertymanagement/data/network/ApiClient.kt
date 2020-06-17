package com.example.skylinepropertymanagement.data.network

import com.example.skylinepropertymanagement.app.Config
import com.example.skylinepropertymanagement.data.model.LoginResponse
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiClient {

    @GET("pro_mgt_reg.php")
    fun register(@Query("email")email:String, @Query("landlord_email")landEmail:String, @Query("password")password:String, @Query("account_for")account:String): Observable<String>

    @GET("pro_mgt_login.php")
    fun login(@Query("email")email:String, @Query("password")password:String):Observable<LoginResponse>

    companion object {
        operator fun invoke():ApiClient {
            return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                .baseUrl(Config.BASE_URL)
                .build()
                .create(ApiClient::class.java)
        }
    }
}