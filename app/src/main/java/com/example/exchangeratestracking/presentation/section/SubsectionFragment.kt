package com.example.exchangeratestracking.presentation.section

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.exchangeratestracking.R

class SubsectionFragment : Fragment() {

    companion object {
        fun newInstance() = SubsectionFragment()
    }

    private lateinit var viewModel: SubsectionViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_subsection, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SubsectionViewModel::class.java)
        // TODO: Use the ViewModel
    }

}