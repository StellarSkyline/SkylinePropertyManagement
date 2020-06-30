package com.example.skylinepropertymanagement.app

import android.app.Application
import com.example.skylinepropertymanagement.data.dagger.component.AppComponent
import com.example.skylinepropertymanagement.data.dagger.component.DaggerAppComponent
import com.example.skylinepropertymanagement.data.dagger.module.AppModule

class App:Application() {
    override fun onCreate() {
        instance = this
        component = DaggerAppComponent.builder().appModule(AppModule()).build()
        super.onCreate()
    }

    companion object {
        lateinit var instance:App
        lateinit var component:AppComponent
    }

}