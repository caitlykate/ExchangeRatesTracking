package com.example.exchangeratestracking.presentation.section

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.exchangeratestracking.R
import com.example.exchangeratestracking.presentation.BaseFragment

class SectionFragment : BaseFragment() {

    override val layout = R.layout.fragment_section

    companion object {
        fun newInstance() = SectionFragment()
    }

    private lateinit var viewModel: SectionViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SectionViewModel::class.java)
        // TODO: Use the ViewModel
    }

}