package com.example.exchangeratestracking.domain.usecase

import com.example.exchangeratestracking.data.local.db.MainDataBase
import com.example.exchangeratestracking.data.local.db.entity.FavouriteCurrencyEntity
import javax.inject.Inject

class DeleteFavCurrencyUseCase @Inject constructor(
    private val db: MainDataBase
)  {

    suspend fun execute(currency: String) {
        db.getFavouriteCurrenciesDao().deleteFavCurrency(FavouriteCurrencyEntity(charCode = currency))
    }
}