package com.example.exchangeratestracking.domain.interactor

import android.util.Log
import com.example.exchangeratestracking.data.remote.api.ExchangeRatesApiService
import com.example.exchangeratestracking.domain.mapper.CurrentRateResponseToMapExchangeRateMapper
import com.example.exchangeratestracking.presentation.entity.ExchangeRate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCurrentRatesInteractor @Inject constructor(
    val apiService: ExchangeRatesApiService,
    val mapper: CurrentRateResponseToMapExchangeRateMapper
) {

    suspend fun getCurrentRates(base: String) : List<ExchangeRate> {
        val response = apiService.getCurrentRate(base)
        Log.d("test", "$response")
        return mapper(response.body()!!)
    }
}