package com.example.exchangeratestracking.presentation.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exchangeratestracking.R
import com.example.exchangeratestracking.di.withFactory
import com.example.exchangeratestracking.presentation.BaseFragment
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : BaseFragment() {

    override val layout: Int = R.layout.fragment_home

    @Inject
    lateinit var homeViewModelFactory: HomeViewModel.Factory

    private val homeViewModel: HomeViewModel by viewModels { withFactory(homeViewModelFactory) }

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
            homeViewModel.exchangeRates.collect { data ->
                adapter.exchangeRateList = data
            }
        }
    }

}