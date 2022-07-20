package com.example.exchangeratestracking.presentation.entity

import androidx.annotation.StringRes
import com.example.exchangeratestracking.R

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
    val sort: SortType,
    val currency: String,
)

class ExchangeRatesUiState(
    val rates: List<ExchangeRate>,
    val isLoaderVisible: Boolean,
    val hasError: Boolean,
    val sort: SortType, // TODO   В сцщность
    val currency: String,
)

enum class SortType(@StringRes val titleRes: Int) {
    AZ(titleRes = R.string.sort_type_az),
    ZA(titleRes = R.string.sort_type_za),
    ASC(titleRes = R.string.sort_type_asc),
    DESC(titleRes = R.string.sort_type_desc)
}

//
//enum class Sort {
//    A, B, C
//}

