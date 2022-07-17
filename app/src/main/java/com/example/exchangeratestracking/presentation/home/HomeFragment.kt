package com.example.exchangeratestracking.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exchangeratestracking.R
import com.example.exchangeratestracking.appComponent
import com.example.exchangeratestracking.di.component.DaggerHomeScreenComponent
import com.example.exchangeratestracking.presentation.BaseFragment
import com.example.exchangeratestracking.presentation.entity.ExchangeRatesUiState
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {

    override val layout: Int = R.layout.fragment_home

    private val requestCurrency: String = "RUB"

//    private val listOfCurrencies = mutableListOf<Currency>()
//    private val listOfCurrencies = mutableListOf<String>()

    private val component by lazy {
        DaggerHomeScreenComponent.builder()
            .service(activity?.appComponent!!.apiService())
            .build()
    }

    private val viewModel by viewModels<HomeViewModel> {
        component.viewModelFactory()
    }

    private val adapter by lazy {
        HomeAdapter{ exchangeRate ->
            Log.d("test","$exchangeRate")
            TODO()
        }
    }

    private val spinnerAdapter = SpinnerAdapter(listOfCurrencies)
//    private val spinnerAdapter = SpinnerAdapter()


    override fun onSetupLayout() {
        recyclerViewContent.adapter = adapter
        spinnerCurrency.adapter = spinnerAdapter
    }

    override fun onCollectFlow() {
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect{ state ->
                when (state) {
                    is ExchangeRatesUiState.Loaded -> {
                        recyclerViewContent.visibility = View.VISIBLE
                        emptyTV.visibility = View.GONE
                        progressBar.visibility = View.GONE
                        errorTV.visibility = View.GONE
                        adapter.exchangeRateList = state.data
//                        state.data.forEach { rate ->
////                            listOfCurrencies.add(Currency(rate.currency, false))
//                            listOfCurrencies.add(rate.currency)
//                        }
//                        listOfCurrencies[0].isSelected = true
//
//                        spinnerAdapter.items = listOfCurrencies as ArrayList<String>
//                        spinnerCurrency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
//                            override fun onItemSelected(
//                                parent: AdapterView<*>?,
//                                view: View?,
//                                position: Int,
//                                id: Long
//                            ) {
//                                val currency = listOfCurrencies[position]
//                            }
//
//                            override fun onNothingSelected(parent: AdapterView<*>?) {
//                            }
//
//                        }
                    }
                    is ExchangeRatesUiState.Loading -> {
                        recyclerViewContent.visibility = View.GONE
                        emptyTV.visibility = View.GONE
                        progressBar.visibility = View.VISIBLE
                        errorTV.visibility = View.GONE
                    }
                    is ExchangeRatesUiState.Empty -> {
                        recyclerViewContent.visibility = View.GONE
                        emptyTV.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        errorTV.visibility = View.GONE
                        adapter.exchangeRateList = emptyList()
                    }
                    is ExchangeRatesUiState.Error -> {
                        recyclerViewContent.visibility = View.GONE
                        emptyTV.visibility = View.GONE
                        progressBar.visibility = View.GONE
                        errorTV.visibility = View.VISIBLE
                    }
                }
            }
        }
    }
    companion object{
        private val listOfCurrencies = listOf(
            "AMD",
            "AUD",
            "AZN",
            "BGN",
            "BRL",
            "BYN",
            "CAD",
            "CHF",
            "CNY",
            "CZK",
            "DKK",
            "EUR",
            "GBP",
            "HKD",
            "HUF",
            "INR",
            "JPY",
            "KGS",
            "KRW",
            "KZT",
            "MDL",
            "NOK",
            "PLN",
            "RON",
            "RUB",
            "SEK",
            "SGD",
            "TJS",
            "TMT",
            "TRY",
            "UAH",
            "USD",
            "UZS",
            "XDR",
            "ZAR",
            "ZWL"
        )
    }
}
//
//data class Currency(
//    val name: String,
//    var isSelected: Boolean
//) : SpinnerListItem {
//
//    override fun getItemTitle(): String = name
//
//    override fun isSelected(selectedId: String?): Boolean {
//        return isSelected
//    }
//}
//
//data class SortBy(
//    val name: String,
//    var isSelected: Boolean
//) : SpinnerListItem {
//
//    override fun getItemTitle(): String = name
//
//    override fun isSelected(selectedId: String?): Boolean {
//        return isSelected
//    }
//}