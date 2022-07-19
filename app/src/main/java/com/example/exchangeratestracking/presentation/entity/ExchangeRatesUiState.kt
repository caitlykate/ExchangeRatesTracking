package com.example.exchangeratestracking.presentation.entity

//sealed class ExchangeRatesUiState {
//    object Empty : ExchangeRatesUiState()
//    object Loading : ExchangeRatesUiState()
//    class Loaded(val data: List<ExchangeRate>) : ExchangeRatesUiState()
//    object Error : ExchangeRatesUiState()
//}

//class SortPanelState(
//    val requestCurrency: String,
//    val chosenSort: Int
//)

sealed class LoadingState {
    object Loading: LoadingState()
    object Error: LoadingState()
    object Success: LoadingState()
}

data class ExchangeRatesState(
    val rates: List<ExchangeRate>,
    val loadingState: LoadingState,
    val sort: Int,
    val currency: String,
)

class ExchangeRatesUiState(
    val rates: List<ExchangeRate>,
    val isLoaderVisible: Boolean,
    val hasError: Boolean,
    val sort: Int, // TODO   В сцщность
    val currency: String,
)

//
//enum class Sort {
//    A, B, C
//}

