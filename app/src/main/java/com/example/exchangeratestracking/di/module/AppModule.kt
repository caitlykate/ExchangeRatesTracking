package com.example.exchangeratestracking.di.module

//import android.content.Context
//import androidx.room.Room
//import com.example.exchangeratestracking.data.local.db.MainDataBase
import com.example.exchangeratestracking.data.remote.api.ExchangeRatesApiService
import com.example.exchangeratestracking.data.remote.api.ExchangeRatesApiServiceFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideApiService(): ExchangeRatesApiService {
        return ExchangeRatesApiServiceFactory.newInstance()
    }
//
//    @Provides
//    @Singleton
//    fun provideDb(context: Context): MainDataBase {
//        return Room.databaseBuilder(
//            context,
//            MainDataBase::class.java,
//            MainDataBase.DB_NAME
//        ).build()
//    }
}