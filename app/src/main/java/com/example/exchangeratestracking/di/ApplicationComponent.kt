package com.example.exchangeratestracking.di

import com.example.exchangeratestracking.di.module.ApplicationModule
import dagger.Component
import retrofit2.Retrofit

@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    val retrofit: Retrofit
}