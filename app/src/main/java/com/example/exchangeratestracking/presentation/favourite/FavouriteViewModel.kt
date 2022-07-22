package com.example.exchangeratestracking.presentation.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exchangeratestracking.domain.usecase.rates.GetCurrentRatesUseCase
import javax.inject.Inject

class FavouriteViewModel@Inject constructor(
    private val getCurrentRatesUseCase: GetCurrentRatesUseCase
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is favourite rates Fragment"
    }
    val text: LiveData<String> = _text
}