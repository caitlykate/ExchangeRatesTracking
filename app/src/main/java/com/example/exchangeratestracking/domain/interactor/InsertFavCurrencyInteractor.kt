package com.example.exchangeratestracking.domain.interactor

import com.example.exchangeratestracking.data.local.db.MainDataBase
import com.example.exchangeratestracking.data.local.db.entity.FavouriteCurrencyEntity
import javax.inject.Inject

interface InsertFavCurrencyInteractor {

    suspend fun insert(currency: String)
}

class InsertFavCurrencyInteractorImpl @Inject constructor(
    private val db: MainDataBase
) : InsertFavCurrencyInteractor {

    override suspend fun insert(currency: String) {
        db.getFavouriteCurrenciesDao().insertFavCurrency(FavouriteCurrencyEntity(charCode = currency))
    }
}