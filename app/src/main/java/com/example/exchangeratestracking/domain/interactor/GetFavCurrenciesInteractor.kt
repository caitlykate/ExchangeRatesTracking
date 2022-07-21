package com.example.exchangeratestracking.domain.interactor

import com.example.exchangeratestracking.data.local.db.MainDataBase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface GetFavCurrenciesInteractor {

    fun get() : Flow<List<String>>
}

class GetFavCurrenciesInteractorImpl @Inject constructor(
    private val db: MainDataBase
    ) : GetFavCurrenciesInteractor {

    override fun get(): Flow<List<String>> {
        return db.getFavouriteCurrenciesDao().getAllFavCurrencies().map { favouriteCurrencyEntityList ->
            favouriteCurrencyEntityList.map { favouriteCurrencyEntity ->
                favouriteCurrencyEntity.toString()
            }
        }
    }
}