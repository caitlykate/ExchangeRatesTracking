package com.example.exchangeratestracking.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exchangeratestracking.R
import com.example.exchangeratestracking.appComponent
import com.example.exchangeratestracking.databinding.FragmentHomeBinding
import com.example.exchangeratestracking.di.component.DaggerHomeScreenComponent
import com.example.exchangeratestracking.presentation.BaseFragment
import com.example.exchangeratestracking.presentation.ExchangeRatesUiState
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
            TODO()
        }
    }

    override fun onSetupLayout() {
        recyclerViewContent.adapter = adapter
    }

    override fun onCollectFlow() {
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect{ state ->
//                when (state) {
//                    ExchangeRatesUiState.Loaded{} -> progressBar.visibility = View.VISIBLE
//                    ExchangeRatesUiState.Loading -> progressBar.visibility = View.VISIBLE
//                    ExchangeRatesUiState.Empty -> progressBar.visibility = View.VISIBLE
//                    ExchangeRatesUiState.Error -> progressBar.visibility = View.VISIBLE
//                }
            }
        }
    }

}