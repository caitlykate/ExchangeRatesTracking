package com.example.exchangeratestracking.di

import com.example.exchangeratestracking.data.remote.api.ExchangeRatesApiService
import com.example.exchangeratestracking.di.module.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    val exchangeRatesApiService: ExchangeRatesApiService
}