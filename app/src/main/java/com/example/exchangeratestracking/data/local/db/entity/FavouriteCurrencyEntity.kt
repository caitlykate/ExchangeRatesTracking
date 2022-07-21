package com.example.exchangeratestracking.data.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "favourite_currencies")
data class FavouriteCurrencyEntity(

    @PrimaryKey
    @ColumnInfo(name = "char_code")
    val charCode: String
) : Serializable
