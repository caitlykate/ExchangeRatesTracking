package com.example.exchangeratestracking.presentation.favourite

import android.view.View
import android.widget.AdapterView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.exchangeratestracking.R
import com.example.exchangeratestracking.appComponent
import com.example.exchangeratestracking.di.component.DaggerFavouriteScreenComponent
import com.example.exchangeratestracking.presentation.BaseFragment
import com.example.exchangeratestracking.presentation.SpinnerAdapter
import com.example.exchangeratestracking.presentation.entity.SortType
import com.example.exchangeratestracking.presentation.home.HomeAdapter
import com.example.exchangeratestracking.presentation.sort.SortFragment
import kotlinx.android.synthetic.main.fragment_base.*

class FavouriteFragment : BaseFragment() {

    override val layout: Int = R.layout.fragment_base

    lateinit var sort: SortType

    private val component by lazy {
        DaggerFavouriteScreenComponent.builder()
            .service(activity?.appComponent!!.apiService())
            .db(activity?.appComponent!!.db())
            .build()
    }

    private val viewModel by viewModels<FavouriteViewModel> {
        component.viewModelFactory()
    }

    private val adapter by lazy {
        HomeAdapter { currency, isPressed ->
            viewModel.onFavClick(currency, isPressed)
        }
    }

    private val spinnerAdapter by lazy {
        SpinnerAdapter(viewModel.favCurrenciesStateFlow.value)
    }
//
//    private  lateinit var spinnerAdapter : SpinnerAdapter

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
                recyclerViewContent.isVisible = !uiState.rates.isEmpty()
                emptyTV.isVisible = uiState.rates.isEmpty()
                progressBar.isVisible = uiState.isLoaderVisible
                errorLayout.isVisible = uiState.hasError
                adapter.exchangeRates = uiState.rates
                sort = uiState.sort
                textViewSort.text = getString(sort.titleRes)

            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.favCurrenciesStateFlow.collect { favCurrencies ->
                adapter.favRates = favCurrencies
                spinnerAdapter.newItems = favCurrencies
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
                viewModel.onNewCurrencyClick(currency = spinnerCurrency.adapter.getItem(position).toString()) //мб лучше брать из состояния во viewModel
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
        textViewSort.setOnClickListener {
            openSortFrag()
        }
        errorBtn.setOnClickListener {
            viewModel.onRefresh()
        }
        ratesSwipeRefreshLayout.setOnRefreshListener {
            ratesSwipeRefreshLayout.isRefreshing = false
            viewModel.onRefresh()
        }
    }

    //добавить анимацию
    private fun openSortFrag() {

        findNavController().navigate(
            R.id.action_navigation_favourite_to_sortFragment,
            bundleOf(SortFragment.SORT_TYPE to sort)
        )
    }
}