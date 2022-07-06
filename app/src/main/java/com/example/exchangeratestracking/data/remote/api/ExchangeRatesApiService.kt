package com.example.exchangeratestracking.data.remote.api

import com.example.exchangeratestracking.data.remote.responce.GetCurrentRateResponce
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeRatesApiService {

    @GET("latest")    //https://api.exchangerate.host/latest?base=RUB
    suspend fun getCurrentRate(
        @Query("base") base: String
    ): GetCurrentRateResponce
}