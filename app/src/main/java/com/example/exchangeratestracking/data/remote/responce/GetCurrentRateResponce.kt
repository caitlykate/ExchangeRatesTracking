package com.example.exchangeratestracking.data.remote.responce

data class GetCurrentRateResponce(
    val base: String,
    val date: String,
    val motd: Motd,
    val rates: Rates,
    val success: Boolean
)