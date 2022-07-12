package com.example.exchangeratestracking.domain.interactor

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

//    fun getCurrentRates(base: String) : Flow<List<ExchangeRate>> {
//        return flow {
//            emit(mapper(apiService.getCurrentRate(base).body()!!))
//        }
//    }

    suspend fun getCurrentRates(base: String) : List<ExchangeRate> {
        return mapper(apiService.getCurrentRate(base).body()!!)

    }
}