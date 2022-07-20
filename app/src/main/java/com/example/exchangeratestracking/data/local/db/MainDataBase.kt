package com.example.exchangeratestracking.data.local.db

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.exchangeratestracking.data.local.db.entity.FavouriteCurrenciesEntity

@Database(
    entities = [
        FavouriteCurrenciesEntity::class,
    ],
    version = 1,
)
abstract class MainDataBase : RoomDatabase() {

    abstract fun getDao(): Dao

    companion object {

        const val DB_NAME = "currencies.db"

        @Volatile
        private var INSTANCE: MainDataBase? =
            null      //эту переменную будем возвращать когда запрашиваем БД

        fun getDataBase(context: Context): MainDataBase {
            return INSTANCE
                ?: synchronized(this) {     //если несколько потоков пытаются запустить, то выполняем только в первом, в скобках чем блокируем (этим классом)
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        MainDataBase::class.java,
                        DB_NAME
                    ).build()
                    instance
                }
        }
    }
}