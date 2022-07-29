package com.example.exchangeratestracking.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exchangeratestracking.domain.usecase.DeleteFavCurrencyUseCase
import com.example.exchangeratestracking.domain.usecase.rates.GetCurrentRatesUseCase
import com.example.exchangeratestracking.domain.usecase.favourite.GetFavCurrenciesUseCase
import com.example.exchangeratestracking.domain.usecase.InsertFavCurrencyUseCase
import com.example.exchangeratestracking.presentation.entity.*
import com.example.exchangeratestracking.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getCurrentRatesUseCase: GetCurrentRatesUseCase,
    private val getFavCurrenciesUseCase: GetFavCurrenciesUseCase,
    private val insertFavCurrencyUseCase: InsertFavCurrencyUseCase,
    private val deleteFavCurrencyUseCase: DeleteFavCurrencyUseCase,
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

    private val favCurrenciesMutableStateFlow: MutableStateFlow<List<String>> = MutableStateFlow(emptyList())
    val favCurrenciesStateFlow: StateFlow<List<String>> = favCurrenciesMutableStateFlow

    init {
        fetchRates(currency = exchangeRatesStateMutableStateFlow.value.currency)
        fetchFavCurrencies()
    }

    private fun fetchRates(currency: String) {
        exchangeRatesStateMutableStateFlow.value = exchangeRatesStateMutableStateFlow.value.copy(
            currency = currency,
            loadingState = LoadingState.Loading,
        )
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val newRates = getCurrentRatesUseCase.execute(base = currency)

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

    fun onFavClick(
        currency: String,
//                   isPressed: Boolean
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                if (currency in favCurrenciesMutableStateFlow.value) {
                    deleteFavCurrencyUseCase.execute(currency)
                } else {
                    insertFavCurrencyUseCase.execute(currency)
                }
            } catch (ex: Exception) {
                ex.message?.let { Log.e("error", it) }
                //TODO вывод ошибки
            }
            fetchFavCurrencies()
        }
    }

    private fun fetchFavCurrencies() {
        viewModelScope.launch(Dispatchers.IO) {
            getFavCurrenciesUseCase.execute().collect { favCurrencies ->
                favCurrenciesMutableStateFlow.value = favCurrencies
            }
        }
    }

    fun onRefresh() {
        fetchRates(currency = exchangeRatesStateMutableStateFlow.value.currency)
    }

    fun onNewCurrencyClick(currencyName: String) {
        if (currencyName != exchangeRatesStateMutableStateFlow.value.currency) {
            fetchRates(currency = currencyName)
        }
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

        val sortedList = viewModelScope.async(Dispatchers.Default) {
            rates.sortedWith(
                when (sortType) {
                    SortType.AZ -> Utils.ExchangeRateAZComparator
                    SortType.ZA -> Utils.ExchangeRateZAComparator
                    SortType.ASC -> Utils.ExchangeRateAscComparator
                    SortType.DESC -> Utils.ExchangeRateDescComparator
                }
            )
        }

        return sortedList.await()
    }
}