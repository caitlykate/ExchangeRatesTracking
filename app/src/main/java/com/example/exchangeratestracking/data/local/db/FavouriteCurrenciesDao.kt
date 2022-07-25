package com.example.exchangeratestracking.data.local.db

import androidx.room.*
import androidx.room.Dao
import com.example.exchangeratestracking.data.local.db.entity.FavouriteCurrencyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteCurrenciesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavCurrency(currency: FavouriteCurrencyEntity)

    @Query(value = "SELECT * FROM favourite_currencies")
    fun getAllFavCurrencies(): Flow<List<FavouriteCurrencyEntity?>>

    @Delete
    suspend fun deleteFavCurrency(favCurrency: FavouriteCurrencyEntity)
}