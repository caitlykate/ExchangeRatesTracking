package com.example.exchangeratestracking.presentation.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exchangeratestracking.R
import com.example.exchangeratestracking.di.component.HomeScreenComponent
import com.example.exchangeratestracking.presentation.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {
//
//    private val component by lazy {
//        Da
//    }

    override val layout: Int = R.layout.fragment_home

    private val viewModel by viewModels<HomeViewModel> ()

    private val adapter by lazy {
        HomeAdapter{ exchangeRate ->
            TODO()
        }
    }

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onSetupLayout() {
        recyclerViewContent.layoutManager = LinearLayoutManager(activity)
        recyclerViewContent.adapter = adapter
    }

    override fun onObserveLiveData() {

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.exchangeRates.collect { data ->
                adapter.exchangeRateList = data
            }
        }
    }

}