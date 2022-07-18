package com.example.exchangeratestracking.utils

import com.example.exchangeratestracking.presentation.entity.ExchangeRate

class Utils {

    object ExchangeRateAscComparator : Comparator<ExchangeRate> {
        override fun compare(first: ExchangeRate, second: ExchangeRate): Int {
            return first.value.compareTo(second.value)
        }
    }

    object ExchangeRateDescComparator : Comparator<ExchangeRate> {
        override fun compare(first: ExchangeRate, second: ExchangeRate): Int {
            return second.value.compareTo(first.value)
        }
    }

    object ExchangeRateAZComparator : Comparator<ExchangeRate> {
        override fun compare(first: ExchangeRate, second: ExchangeRate): Int {
            return first.currency.compareTo(second.currency)
        }
    }

    object ExchangeRateZAComparator : Comparator<ExchangeRate> {
        override fun compare(first: ExchangeRate, second: ExchangeRate): Int {
            return second.currency.compareTo(first.currency)
        }
    }
}