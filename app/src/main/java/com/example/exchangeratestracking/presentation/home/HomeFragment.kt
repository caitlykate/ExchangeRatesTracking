package com.example.exchangeratestracking.presentation.home

import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.exchangeratestracking.R
import com.example.exchangeratestracking.appComponent
import com.example.exchangeratestracking.di.component.DaggerHomeScreenComponent
import com.example.exchangeratestracking.presentation.BaseFragment
import com.example.exchangeratestracking.presentation.entity.SortType
import com.example.exchangeratestracking.presentation.sort.SortFragment
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment() {

    override val layout: Int = R.layout.fragment_home

    private val component by lazy {
        DaggerHomeScreenComponent.builder()
            .service(activity?.appComponent!!.apiService())
            .build()
    }

    private val viewModel by viewModels<HomeViewModel> {
        component.viewModelFactory()
    }

    private val adapter by lazy {
        HomeAdapter { exchangeRate ->
            Log.d("test", "$exchangeRate") //добавить onClick
        }
    }

    private val spinnerAdapter = SpinnerAdapter(listOfCurrencies)

    override fun onSetupLayout() {
        recyclerViewContent.adapter = adapter
        spinnerCurrency.adapter = spinnerAdapter

        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<SortType>(SortFragment.SORT_TYPE)
            ?.observe(viewLifecycleOwner) { sortType ->
                viewModel.onNewSortClick(sortType)
            }
    }

    override fun onCollectFlow() {
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { uiState ->
//                when (uiState.) {
//                    is ExchangeRatesUiState. -> {
//                        recyclerViewContent.visibility = View.VISIBLE
//                        emptyTV.visibility = View.GONE
//                        progressBar.visibility = View.GONE
//                        errorTV.visibility = View.GONE
//                        adapter.exchangeRateList = viewModel.sort(uiState.data)
//                    }
//                    is ExchangeRatesUiState.Loading -> {
//                        recyclerViewContent.visibility = View.GONE
//                        emptyTV.visibility = View.GONE
//                        progressBar.visibility = View.VISIBLE
//                        errorTV.visibility = View.GONE
//                    }
//                    is ExchangeRatesUiState.Empty -> {
//                        recyclerViewContent.visibility = View.GONE
//                        emptyTV.visibility = View.VISIBLE
//                        progressBar.visibility = View.GONE
//                        errorTV.visibility = View.GONE
//                        adapter.exchangeRateList = emptyList()
//                    }
//                    is ExchangeRatesUiState.Error -> {
//                        recyclerViewContent.visibility = View.GONE
//                        emptyTV.visibility = View.GONE
//                        progressBar.visibility = View.GONE
//                        errorTV.visibility = View.VISIBLE
//                    }
//                }
                recyclerViewContent.isVisible = !uiState.rates.isEmpty()
                emptyTV.isVisible = uiState.rates.isEmpty()
                progressBar.isVisible = uiState.isLoaderVisible
                errorTV.isVisible = uiState.hasError
                adapter.exchangeRateList = uiState.rates
                textViewSort.text = getString(uiState.sort.titleRes)
            }
        }
    }


    override fun setOnClicks() {
        spinnerCurrency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.onNewCurrencyClick(listOfCurrencies[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        textViewSort.setOnClickListener {
            openSortFrag()
        }
    }

    //добавить анимацию
    private fun openSortFrag() {
//        findNavController().navigate(
//            R.id.action_navigation_home_to_sortFragment,
//            bundleOf(SortFragment.SORT_TYPE to viewModel.uiState.collect{ uiState ->
//                uiState.sort
//            })
//        )
        lifecycleScope.launch {
            findNavController().navigate(
                R.id.action_navigation_home_to_sortFragment,
                bundleOf(SortFragment.SORT_TYPE to viewModel.uiState.collect{ uiState ->
                    uiState.sort
                })
            )
        }
    }

    companion object {
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
