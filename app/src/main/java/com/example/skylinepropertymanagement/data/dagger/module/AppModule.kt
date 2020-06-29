package com.example.skylinepropertymanagement.data.dagger.module

import android.content.SharedPreferences
import com.example.skylinepropertymanagement.data.SessionManager
import dagger.Module
import dagger.Provides
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
}