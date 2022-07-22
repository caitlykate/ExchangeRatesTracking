package com.example.exchangeratestracking.domain.usecase

import com.example.exchangeratestracking.data.local.db.MainDataBase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFavCurrenciesUseCase @Inject constructor(
    private val db: MainDataBase
    )  {

    fun execute(): Flow<List<String>> {
        return db.getFavouriteCurrenciesDao().getAllFavCurrencies().map { favouriteCurrencyEntityList ->
            favouriteCurrencyEntityList.map { favouriteCurrencyEntity ->
                favouriteCurrencyEntity?.charCode.toString()
            }
        }
    }
}