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
import com.example.exchangeratestracking.presentation.entity.SortType
import kotlinx.android.synthetic.main.fragment_sort.*

class SortFragment : Fragment(){

    companion object {
        fun newInstance() = SortFragment()
        const val SORT_TYPE = "sort_type"
    }

//    private lateinit var viewModel: SortViewModel

    private val valuesAdapter by lazy {
        SortValuesAdapter(
            checkedSortType = requireArguments().getSerializable(SORT_TYPE) as SortType,
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

        recycler_vew_sort.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = valuesAdapter
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        viewModel = ViewModelProvider(this)[SortViewModel::class.java]
    }



}