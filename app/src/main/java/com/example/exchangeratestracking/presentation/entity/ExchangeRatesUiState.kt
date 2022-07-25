package com.example.exchangeratestracking.presentation.entity

import androidx.annotation.StringRes
import com.example.exchangeratestracking.R

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

val listOfCurrencies = listOf(
        "AMD",
        "AUD",
        "AZN",
        "BGN",
        "BRL",
        "BYN",
        "CAD",
        "CHF",
        "CNY",
        "CZK",
        "DKK",
        "EUR",
        "GBP",
        "HKD",
        "HUF",
        "INR",
        "JPY",
        "KGS",
        "KRW",
        "KZT",
        "MDL",
        "NOK",
        "PLN",
        "RON",
        "RUB",
        "SEK",
        "SGD",
        "TJS",
        "TMT",
        "TRY",
        "UAH",
        "USD",
        "UZS",
        "XDR",
        "ZAR",
        "ZWL"
    )
