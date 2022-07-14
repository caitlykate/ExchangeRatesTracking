package com.example.exchangeratestracking.presentation.home

import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.exchangeratestracking.R
import com.example.exchangeratestracking.appComponent
import com.example.exchangeratestracking.di.component.DaggerHomeScreenComponent
import com.example.exchangeratestracking.presentation.BaseFragment
import com.example.exchangeratestracking.presentation.entity.ExchangeRatesUiState
import kotlinx.android.synthetic.main.fragment_home.*

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
        HomeAdapter{ exchangeRate ->
            Log.d("test","$exchangeRate")
            TODO()
        }
    }

    override fun onSetupLayout() {
        recyclerViewContent.adapter = adapter
    }

    override fun onCollectFlow() {
        lifecycleScope.launchWhenStarted() {
            viewModel.uiState.collect{ state ->
                when (state) {
                    is ExchangeRatesUiState.Loaded -> {
                        recyclerViewContent.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        errorTV.visibility = View.GONE
                        adapter.exchangeRateList = state.data
                    }
                    is ExchangeRatesUiState.Loading -> {
                        recyclerViewContent.visibility = View.GONE
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
                        progressBar.visibility = View.GONE
                        errorTV.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

}