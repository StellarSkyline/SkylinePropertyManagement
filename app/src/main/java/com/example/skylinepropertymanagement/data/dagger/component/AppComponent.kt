package com.example.skylinepropertymanagement.data.dagger.component

import androidx.fragment.app.Fragment
import com.example.skylinepropertymanagement.data.dagger.module.AppModule
import com.example.skylinepropertymanagement.data.repo.FragmentRepo
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    //Dependency Graph
    fun inject(Class:FragmentRepo)
}