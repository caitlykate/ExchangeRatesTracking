package com.example.exchangeratestracking.data.remote.responce

data class CurrentRateResponce(
    val base: String,
    val date: String,
    val motd: Motd,
    val rates: Rates,
    val success: Boolean
)