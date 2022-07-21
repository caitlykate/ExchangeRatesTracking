package com.example.exchangeratestracking.domain.interactor

import com.example.exchangeratestracking.data.local.db.MainDataBase
import com.example.exchangeratestracking.data.local.db.entity.FavouriteCurrencyEntity
import javax.inject.Inject

interface DeleteFavCurrencyInteractor {

    suspend fun delete(currency: String)
}

class DeleteFavCurrencyInteractorImpl @Inject constructor(
    private val db: MainDataBase
) : DeleteFavCurrencyInteractor {

    override suspend fun delete(currency: String) {
        db.getFavouriteCurrenciesDao().deleteFavCurrency(FavouriteCurrencyEntity(charCode = currency))
    }
}