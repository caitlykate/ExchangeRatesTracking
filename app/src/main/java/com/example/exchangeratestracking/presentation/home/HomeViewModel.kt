package com.example.exchangeratestracking.presentation.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exchangeratestracking.di.ViewModelFactory
import com.example.exchangeratestracking.domain.interactor.GetCurrentRatesInteractor
import com.example.exchangeratestracking.presentation.entity.ExchangeRate
//import com.example.exchangeratestracking.presentation.ExchangeRatesUiState
//import com.example.exchangeratestracking.presentation.entity.ExchangeRate
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel (private val getCurrentRatesInteractor: GetCurrentRatesInteractor) : ViewModel() {
//
//    private val _uiState = MutableStateFlow<ExchangeRatesUiState>(ExchangeRatesUiState.Empty)
//    val uiState: StateFlow<ExchangeRatesUiState> = _uiState

    private lateinit var _exchangeRates : MutableStateFlow<List<ExchangeRate>>
    val exchangeRates: StateFlow<List<ExchangeRate>> = _exchangeRates

    private var _showProgress = MutableStateFlow(true)
    val showProgress: StateFlow<Boolean> = _showProgress

    init {
        viewModelScope.launch {
            getCurrentRatesInteractor.getCurrentRates("RUB").collect { response ->
                _exchangeRates.compareAndSet(_exchangeRates.value,response)
            }
        }

    }

    fun getRates(){
//        _exchangeRates = apiService.getCurrentRate("RUB")
    }
//
//    fun insertFavCurrency(currency: String) = viewModelScope.launch {            //в корутине
//        dao.insertNote(note)
//    }
class Factory @Inject constructor(
    private val getCurrentRatesInteractor: GetCurrentRatesInteractor
) : ViewModelFactory<HomeViewModel> {
    override fun create(handle: SavedStateHandle): HomeViewModel {
        return HomeViewModel(
            getCurrentRatesInteractor
        )
    }
}
}