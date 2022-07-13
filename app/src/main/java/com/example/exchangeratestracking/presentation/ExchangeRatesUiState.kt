package com.example.exchangeratestracking.presentation

import com.example.exchangeratestracking.presentation.entity.ExchangeRate

sealed class ExchangeRatesUiState {
    object Empty : ExchangeRatesUiState()
    object Loading : ExchangeRatesUiState()
    class Loaded(val data: List<ExchangeRate>) : ExchangeRatesUiState()
    class Error(val message: String) : ExchangeRatesUiState()
}
