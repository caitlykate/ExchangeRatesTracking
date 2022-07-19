package com.example.exchangeratestracking.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exchangeratestracking.domain.interactor.GetCurrentRatesInteractor
import com.example.exchangeratestracking.presentation.entity.ExchangeRate
import com.example.exchangeratestracking.presentation.entity.ExchangeRatesState
import com.example.exchangeratestracking.presentation.entity.ExchangeRatesUiState
import com.example.exchangeratestracking.presentation.entity.LoadingState
import com.example.exchangeratestracking.presentation.entity.SortPanelState
import com.example.exchangeratestracking.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getCurrentRatesInteractor: GetCurrentRatesInteractor
) : ViewModel() {

//
//    var requestCurrency = "AMD"
//        set(value) {
//            field = value
//            fetchRates()
//        }
//
//    var chosenSort = 0

    private val exchangeRatesStateMutableStateFlow = MutableStateFlow(
        ExchangeRatesState(
            rates = emptyList(),
            loadingState = LoadingState.Success,
            sort = 0,
            currency = "", // TODO
        )
    )
    val uiState: Flow<ExchangeRatesUiState> = exchangeRatesStateMutableStateFlow.map { state ->
        with(state) {
            val sortedRates = when (loadingState) {
                is LoadingState.Success -> sortedRates(
                    rates = rates,
                    sortNum = sort,
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

//    private val sortPanelMutableStateFlow = MutableStateFlow(SortPanelState("AMD", 0))
//    val sortPanelStateFlow: StateFlow<SortPanelState> get() = sortPanelMutableStateFlow

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

    fun onNewCurrencyClick(currencyName: String) {
        fetchRates(currency = currencyName)
    }

    fun onNewSortClick(sortNum: Int) {
        exchangeRatesStateMutableStateFlow.value = exchangeRatesStateMutableStateFlow.value.copy(
            sort = sortNum,
        )
    }

    private fun sortedRates(
        rates: List<ExchangeRate>,
        sortNum: Int,
    ): List<ExchangeRate> {
//        rates.sortedWith()

        val sortedList = Collections.sort(
            rates,
            when (sortNum) {
                0 -> Utils.ExchangeRateAZComparator
                1 -> Utils.ExchangeRateZAComparator
                2 -> Utils.ExchangeRateAscComparator
                3 -> Utils.ExchangeRateDescComparator
                else -> error("Unexpected sortNum")
            }
        )
        return rates
    }
}