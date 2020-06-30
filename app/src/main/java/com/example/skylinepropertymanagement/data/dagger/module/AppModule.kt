package com.example.skylinepropertymanagement.data.dagger.module

import com.example.skylinepropertymanagement.app.App
import com.example.skylinepropertymanagement.app.Config
import com.example.skylinepropertymanagement.data.SessionManager
import com.example.skylinepropertymanagement.data.database.DB
import com.example.skylinepropertymanagement.data.network.ApiClient
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
class AppModule {


    @Singleton
    @Provides
    @Named("user")
    fun user():SessionManager {
        return SessionManager()
    }

    @Singleton
    @Provides
    @Named("data")
    fun database():DB {
        return DB.createDatabase(App.instance)
    }

    @Singleton
    @Provides
    @Named("api")
    fun client():ApiClient {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .baseUrl(Config.BASE_URL)
            .build()
            .create(ApiClient::class.java)
    }
}