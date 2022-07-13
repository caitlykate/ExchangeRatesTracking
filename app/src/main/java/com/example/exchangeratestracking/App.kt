package com.example.exchangeratestracking

import android.app.Application
import android.content.Context
import com.example.exchangeratestracking.di.AppComponent
import com.example.exchangeratestracking.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .context(context = this)
            .build()
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> this.applicationContext.appComponent
    }
