package com.example.exchangeratestracking.data.remote.response

data class CurrentRateResponse(
    val base: String,
    val date: String,
    val motd: Motd,
    val rates: Rates,
    val success: Boolean
)