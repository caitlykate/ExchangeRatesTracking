package com.example.exchangeratestracking.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exchangeratestracking.domain.interactor.GetCurrentRatesInteractor
import com.example.exchangeratestracking.presentation.entity.ExchangeRatesUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor (
    private val getCurrentRatesInteractor: GetCurrentRatesInteractor
    ) : ViewModel() {

    private val _uiState = MutableStateFlow<ExchangeRatesUiState>(ExchangeRatesUiState.Empty)
    val uiState: StateFlow<ExchangeRatesUiState> = _uiState

//    private lateinit var _exchangeRates : MutableStateFlow<List<ExchangeRate>>
//    val exchangeRates: StateFlow<List<ExchangeRate>> = _exchangeRates

    init {
        fetchRates()
    }

    private fun fetchRates() {
        _uiState.value = ExchangeRatesUiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
//                _uiState.tryEmit(ExchangeRatesUiState.Loaded(getCurrentRatesInteractor.getCurrentRates("RUB")))
                _uiState.value = ExchangeRatesUiState.Loaded(getCurrentRatesInteractor.getCurrentRates("RUB"))

            } catch (ex: Exception) {
                onErrorOccurred()
            }
        }
    }

    private fun onErrorOccurred() {
        _uiState.value = ExchangeRatesUiState.Error()
    }


}