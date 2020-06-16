package com.example.skylinepropertymanagement.data.network

import com.example.skylinepropertymanagement.app.Config
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

interface ApiClient {

    //Network call methods go here


    companion object {
        operator fun invoke():ApiClient {
            return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Config.BASE_URL)
                .build()
                .create(ApiClient::class.java)
        }
    }
}