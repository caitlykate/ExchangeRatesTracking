package com.example.exchangeratestracking.di

import android.content.Context
import com.example.exchangeratestracking.data.local.db.MainDataBase
import com.example.exchangeratestracking.data.remote.api.ExchangeRatesApiService
import com.example.exchangeratestracking.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun apiService(): ExchangeRatesApiService
    fun db(): MainDataBase

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun context(context: Context) : Builder

        fun build() : AppComponent
    }
}