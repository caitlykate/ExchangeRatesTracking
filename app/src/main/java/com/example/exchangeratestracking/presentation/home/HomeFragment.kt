package com.example.exchangeratestracking.presentation.home

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
import com.example.exchangeratestracking.presentation.ExchangeRatesAdapter
import com.example.exchangeratestracking.presentation.SpinnerAdapter
import com.example.exchangeratestracking.presentation.entity.SortType
import com.example.exchangeratestracking.presentation.entity.listOfCurrencies
import com.example.exchangeratestracking.presentation.sort.SortFragment
import kotlinx.android.synthetic.main.fragment_base.*

class HomeFragment : BaseFragment() {

    override val layout: Int = R.layout.fragment_base

    lateinit var sort: SortType

    private val component by lazy {
        DaggerHomeScreenComponent.builder()
            .service(activity?.appComponent!!.apiService())
            .db(activity?.appComponent!!.db())
            .build()
    }

    private val viewModel by viewModels<HomeViewModel> {
        component.viewModelFactory()
    }

    private val adapter by lazy {
        ExchangeRatesAdapter { currency ->
            viewModel.onFavClick(currency)
        }
    }

    private val spinnerAdapter = SpinnerAdapter(listOfCurrencies)

    override fun onSetupLayout() {
        recycler_view_content.adapter = adapter
        spinner_currency.adapter = spinnerAdapter

        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<SortType>(
            SortFragment.SORT_TYPE
        )
            ?.observe(viewLifecycleOwner) { sortType ->
                viewModel.onNewSortClick(sortType)
            }
    }

    override fun onCollectFlow() {
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { uiState ->
                recycler_view_content.isVisible = !uiState.rates.isEmpty()
                text_view_empty_list.isVisible = uiState.rates.isEmpty()
                progress_bar.isVisible = uiState.isLoaderVisible
                layout_error.isVisible = uiState.hasError
                adapter.exchangeRates = uiState.rates
                sort = uiState.sort
                text_view_sort.text = getString(sort.titleRes)
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.favCurrenciesStateFlow.collect { favCurrencies ->
                adapter.favRates = favCurrencies
            }
        }
    }

    override fun setOnClicks() {
        spinner_currency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                viewModel.onNewCurrencyClick(listOfCurrencies[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        text_view_sort.setOnClickListener {
            openSortFrag()
        }
        button_error.setOnClickListener {
            viewModel.onRefresh()
        }
        swipe_refresh_layout.setOnRefreshListener {
            swipe_refresh_layout.isRefreshing = false
            viewModel.onRefresh()
        }
    }

    private fun openSortFrag() {

        findNavController().navigate(
            R.id.action_navigation_home_to_sortFragment,
            bundleOf(SortFragment.SORT_TYPE to sort),
//           navOptions {
//                anim {
//                    enter = R.anim.enter
//                    exit = R.anim.exit
//                    popEnter = R.anim.pop_enter
//                    popExit = R.anim.pop_exit
//                }
//           }
        )
    }
}
