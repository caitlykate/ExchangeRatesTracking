package com.example.exchangeratestracking.presentation.sort

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exchangeratestracking.R
import kotlinx.android.synthetic.main.fragment_sort.*

class SortFragment : Fragment(){

    companion object {
        fun newInstance() = SortFragment()
        const val SORT_TYPE = "sort_type"
    }

    private lateinit var viewModel: SortViewModel

//    private val sortList : List<CatalogDetailedFilterValue> =
//        listOf(
//            CatalogDetailedFilterValue(0,"A-Z", false),
//            CatalogDetailedFilterValue(1,"Z-A", false),
//            CatalogDetailedFilterValue(2,"По возрастанию значения", false),
//            CatalogDetailedFilterValue(3,"По убыванию значения", false),
//        )

    private var sortTypes = ArrayList<SortType>()

    private val valuesAdapter by lazy {
        SortValuesAdapter(
            sortTypes = sortTypes,
            onItemClick = { sortType ->
                run {
                    findNavController().previousBackStackEntry?.savedStateHandle?.set(
                        SORT_TYPE,
                        sortType
                    )
                    findNavController().popBackStack()
                }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sort, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val new = resources.getStringArray(R.array.sort_types)
        for (i in new.indices) {
            sortTypes.add(SortType(i, new[i], false))
        }

        val sort = requireArguments().getInt(SORT_TYPE)
//
//        sortList[sort].isChecked = true

        recyclerViewFilter.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = valuesAdapter
//                .apply {
////                submitList(sortList)
////            }
            valuesAdapter.checkedItem = sort
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SortViewModel::class.java)
        // TODO: Use the ViewModel
    }
//
//    override fun onMethodCallback(sortBy: Int) {
//        findNavController().previousBackStackEntry?.savedStateHandle?.set(SORT_TYPE, sortBy)
//    }


}