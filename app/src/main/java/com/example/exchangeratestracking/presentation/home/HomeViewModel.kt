package com.example.exchangeratestracking.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exchangeratestracking.data.local.db.MainDataBase
import com.example.exchangeratestracking.domain.interactor.GetCurrentRatesInteractor
import com.example.exchangeratestracking.domain.interactor.GetFavCurrenciesInteractor
import com.example.exchangeratestracking.presentation.entity.*
import com.example.exchangeratestracking.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getCurrentRatesInteractor: GetCurrentRatesInteractor,
    private val getFavCurrenciesInteractor: GetFavCurrenciesInteractor,
) : ViewModel() {

    private val exchangeRatesStateMutableStateFlow = MutableStateFlow(
        ExchangeRatesState(
            rates = emptyList(),
            loadingState = LoadingState.Success,
            sort = SortType.AZ,
            currency = listOfCurrencies.first(),
        )
    )
    val uiState: Flow<ExchangeRatesUiState> = exchangeRatesStateMutableStateFlow.map { state ->
        with(state) {
            val sortedRates = when (loadingState) {
                is LoadingState.Success -> sortedRates(
                    rates = rates,
                    sortType = sort,
                )
                else -> emptyList()
            }

            ExchangeRatesUiState(
                rates = sortedRates,
                isLoaderVisible = loadingState is LoadingState.Loading,
                hasError = loadingState is LoadingState.Error,
                sort = sort,
                currency = currency,
            )
        }
    }
    val favCurrencies = getFavCurrenciesInteractor.get()
        .stateIn(scope = viewModelScope, started = SharingStarted.Lazily, emptyList())

    init {
        fetchRates(currency = exchangeRatesStateMutableStateFlow.value.currency)
    }

    private fun fetchRates(currency: String) {
        exchangeRatesStateMutableStateFlow.value = exchangeRatesStateMutableStateFlow.value.copy(
            currency = currency,
            loadingState = LoadingState.Loading,
        )
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val newRates = getCurrentRatesInteractor.getCurrentRates(base = currency)

                exchangeRatesStateMutableStateFlow.value = exchangeRatesStateMutableStateFlow.value.copy(
                    rates = newRates,
                    loadingState = LoadingState.Success,
                )

            } catch (ex: Exception) {
                exchangeRatesStateMutableStateFlow.value = exchangeRatesStateMutableStateFlow.value.copy(
                    loadingState = LoadingState.Error,
                )
            }
        }
    }
//
//    private fun fetchFavCurrencies(){
//        exchangeRatesStateMutableStateFlow.value = exchangeRatesStateMutableStateFlow.value.copy(
//            loadingState = LoadingState.Loading,
//        )
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                val favCurrecies = dataBase.getFavouriteCurrenciesDao().getAllFavCurrencies()
//
//                exchangeRatesStateMutableStateFlow.value = exchangeRatesStateMutableStateFlow.value.copy(
//                    rates = newRates,
//                    loadingState = LoadingState.Success,
//                )
//
//            } catch (ex: Exception) {
//                exchangeRatesStateMutableStateFlow.value = exchangeRatesStateMutableStateFlow.value.copy(
//                    loadingState = LoadingState.Error,
//                )
//            }
//        }
//    }

    fun onRefresh() {
        fetchRates(currency = exchangeRatesStateMutableStateFlow.value.currency)
    }

    fun onNewCurrencyClick(currencyName: String) {
        fetchRates(currency = currencyName)
    }

    fun onNewSortClick(sortType: SortType) {
        exchangeRatesStateMutableStateFlow.value = exchangeRatesStateMutableStateFlow.value.copy(
            sort = sortType,
        )
    }

    private suspend fun sortedRates(
        rates: List<ExchangeRate>,
        sortType: SortType,
    ): List<ExchangeRate> {

        val sortedList = viewModelScope.async(Dispatchers.Default) { rates.sortedWith(
            when (sortType) {
                SortType.AZ -> Utils.ExchangeRateAZComparator
                SortType.ZA -> Utils.ExchangeRateZAComparator
                SortType.ASC -> Utils.ExchangeRateAscComparator
                SortType.DESC -> Utils.ExchangeRateDescComparator
            }
        ) }

        return sortedList.await()
    }
}