package com.example.exchangeratestracking.domain.interactor

import com.example.exchangeratestracking.data.local.db.MainDataBase

//from db
interface GetFavCurrenciesInteractor {

    fun getFavCurrencies() : List<String>
}

class GetFavCurrenciesInteractorImpl(private val db: MainDataBase) : GetFavCurrenciesInteractor {

    override fun getFavCurrencies(): List<String> {
        return db.getDao().getAllFavCurrencies()
    }

}