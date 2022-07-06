package com.example.exchangeratestracking.domain.interactor

import com.example.exchangeratestracking.data.remote.api.ExchangeRatesApiService

interface UpdateRatesInteractor {

    fun updateRates() : Boolean
}

class UpdateRatesInteractorImpl(
    private val api: ExchangeRatesApiService,
//    private val db: MainDataBase,
//    private val weatherDomainMapper: (CityWeatherData) -> CityWeatherEntity
) : UpdateRatesInteractor {

    override fun updateRates(): Boolean {
        TODO("Not yet implemented")
    }

}