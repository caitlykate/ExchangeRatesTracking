package com.example.exchangeratestracking.di

import android.content.Context
import com.example.exchangeratestracking.data.remote.api.ExchangeRatesApiService
import com.example.exchangeratestracking.di.module.AppModule
import com.example.exchangeratestracking.di.module.FragmentModule
import com.example.exchangeratestracking.presentation.home.HomeViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    FragmentModule::class])
interface AppComponent {

    fun homeViewModel(): HomeViewModel

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun context(context: Context) : Builder

        fun build() : AppComponent
    }
}