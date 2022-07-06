package com.example.exchangeratestracking.data.remote.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ExchangeRatesApiServiceFactory {
    private const val CONNECTION_TIMEOUT_MS = 3_000L
    private const val BASE_URL = "https://api.exchangerate.host/latest"

    fun newInstance(): ExchangeRatesApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ExchangeRatesApiService::class.java)
    }

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(CONNECTION_TIMEOUT_MS, TimeUnit.MILLISECONDS)
            .build()
    }
}