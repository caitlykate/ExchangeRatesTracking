package com.example.exchangeratestracking.data.local.db

//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//import androidx.room.Update
//import com.example.exchangeratestracking.data.local.db.entity.FavouriteCurrenciesEntity

//@Dao
//interface Dao {
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertFavCurrency(currency: FavouriteCurrenciesEntity)
//
//    @Query(value = "SELECT * FROM favourite_currencies")
//    fun getAllFavCurrencies(): List<FavouriteCurrenciesEntity>
//
//    @Update
//    suspend fun updateFavCurrencies(cityWeather: FavouriteCurrenciesEntity)
//}