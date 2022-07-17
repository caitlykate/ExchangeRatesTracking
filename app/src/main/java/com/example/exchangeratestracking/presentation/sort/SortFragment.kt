package com.example.exchangeratestracking.presentation.sort

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.exchangeratestracking.R

class SortFragment : Fragment() {

    companion object {
        fun newInstance() = SortFragment()
        const val SORT_TYPE = "sort_type"
    }

    private lateinit var viewModel: SortViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sort, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sort = requireArguments().getInt(SORT_TYPE)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SortViewModel::class.java)
        // TODO: Use the ViewModel
    }

}