package com.example.exchangeratestracking.presentation.entity

sealed class ExchangeRatesUiState {
    object Empty : ExchangeRatesUiState()
    object Loading : ExchangeRatesUiState()
    class Loaded(val data: List<ExchangeRate>) : ExchangeRatesUiState()
    object Error : ExchangeRatesUiState()
}
