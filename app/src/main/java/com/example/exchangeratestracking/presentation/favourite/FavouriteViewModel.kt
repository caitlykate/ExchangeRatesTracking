package com.example.exchangeratestracking.presentation.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exchangeratestracking.domain.usecase.DeleteFavCurrencyUseCase
import com.example.exchangeratestracking.domain.usecase.favourite.GetFavCurrenciesUseCase
import com.example.exchangeratestracking.domain.usecase.rates.GetCurrentRatesUseCase
import com.example.exchangeratestracking.presentation.entity.ExchangeRate
import com.example.exchangeratestracking.presentation.entity.ExchangeRatesState
import com.example.exchangeratestracking.presentation.entity.ExchangeRatesUiState
import com.example.exchangeratestracking.presentation.entity.LoadingState
import com.example.exchangeratestracking.presentation.entity.SortType
import com.example.exchangeratestracking.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavouriteViewModel @Inject constructor(
    private val getCurrentRatesUseCase: GetCurrentRatesUseCase,
    private val getFavCurrenciesUseCase: GetFavCurrenciesUseCase,
    private val deleteFavCurrencyUseCase: DeleteFavCurrencyUseCase,
) : ViewModel() {

    private val exchangeRatesStateMutableStateFlow = MutableStateFlow(
        ExchangeRatesState(
            rates = mutableListOf(),
            loadingState = LoadingState.Success,
            sort = SortType.AZ,
            currency = "",
        )
    )
    val uiState: Flow<ExchangeRatesUiState> = exchangeRatesStateMutableStateFlow.map { state ->
        with(state) {
            val sortedRates = when (loadingState) {
                is LoadingState.Success -> sortedRates(
                    rates = rates,
                    sortType = sort,
                )
                else -> mutableListOf()
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

    private val favCurrenciesMutableStateFlow: MutableStateFlow<List<String>> =
        MutableStateFlow(emptyList())
    val favCurrenciesStateFlow: StateFlow<List<String>> = favCurrenciesMutableStateFlow

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val job = launch { fetchFavCurrencies() }
            job.join()
            fetchRates(currency = exchangeRatesStateMutableStateFlow.value.currency)
        }
    }

    private fun fetchRates(currency: String) {
        exchangeRatesStateMutableStateFlow.value = exchangeRatesStateMutableStateFlow.value.copy(
            currency = currency,
            loadingState = LoadingState.Loading,
        )
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val newRates = getCurrentRatesUseCase.execute(base = currency)
                    .filter {
                        it.currency in favCurrenciesMutableStateFlow.value
                    }

                exchangeRatesStateMutableStateFlow.value =
                    exchangeRatesStateMutableStateFlow.value.copy(
                        rates = newRates,
                        loadingState = LoadingState.Success,
                    )

            } catch (ex: Exception) {
                exchangeRatesStateMutableStateFlow.value =
                    exchangeRatesStateMutableStateFlow.value.copy(
                        loadingState = LoadingState.Error,
                    )
            }
        }
    }

    fun onFavClick(currency: String, spinnerSelectedItem: () -> String) {
        var setNewRate = false
        viewModelScope.launch(Dispatchers.IO) {
            try {
                deleteFavCurrencyUseCase.execute(currency = currency)
                if (currency == exchangeRatesStateMutableStateFlow.value.currency) {
                    setNewRate = true
                }
                exchangeRatesStateMutableStateFlow.value =
                    exchangeRatesStateMutableStateFlow.value.copy(
                        rates = exchangeRatesStateMutableStateFlow.value.rates.filter { exchangeRate -> exchangeRate.currency != currency }
                    )
                favCurrenciesMutableStateFlow.value.filter { cur -> cur != currency }
            } catch (ex: Exception) {
                //TODO вывод ошибки в тосте
            }
            if (setNewRate && exchangeRatesStateMutableStateFlow.value.rates.isNotEmpty()) {
                Thread.sleep(30)    //костыль (потому что спиннер сам меняет валюту в главном потоке без вызова onItemSelectedListener, в идеале мы должны сами это устанавливать из фрагмента)
                fetchRates(currency = spinnerSelectedItem())
            }
        }
    }

    private suspend fun fetchFavCurrencies() {
        getFavCurrenciesUseCase.execute().collect { favCurrencies ->
            favCurrenciesMutableStateFlow.value = favCurrencies.sorted()
        }
    }

    fun onRefresh() {
        fetchRates(currency = exchangeRatesStateMutableStateFlow.value.currency)
    }

    fun onNewCurrencyClick(currency: String) {
        exchangeRatesStateMutableStateFlow.value = exchangeRatesStateMutableStateFlow.value.copy(
            currency = currency,
        )
        fetchRates(currency = currency)
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