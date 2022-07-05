package com.example.exchangeratestracking.presentation.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FavouriteViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is favourite rates Fragment"
    }
    val text: LiveData<String> = _text
}