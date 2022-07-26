package com.example.exchangeratestracking.domain.usecase.rates

import android.util.Log
import com.example.exchangeratestracking.data.remote.api.ExchangeRatesApiService
import com.example.exchangeratestracking.domain.mapper.CurrentRateResponseToMapExchangeRateMapper
import com.example.exchangeratestracking.presentation.entity.ExchangeRate
import javax.inject.Inject

class GetCurrentRatesUseCase @Inject constructor(
    private val apiService: ExchangeRatesApiService,
    val mapper: CurrentRateResponseToMapExchangeRateMapper
) {

    suspend fun execute(base: String) : List<ExchangeRate> {
        val response = apiService.getCurrentRate(base)
        Log.d("test", "$response")
        return mapper(response.body()!!)
    }
}