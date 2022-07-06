package com.example.exchangeratestracking.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.*

class HomeViewModel : ViewModel() {

    private val dao = dataBase.getDao()

    private val _exchangeRates = MutableStateFlow<String>("").apply {
        value = "This is home Fragment"
    }
    val exchangeRates: StateFlow<String> = _exchangeRates


    fun insertFavCurrency(currency: String) = viewModelScope.launch {            //в корутине
        dao.insertNote(note)
    }
}