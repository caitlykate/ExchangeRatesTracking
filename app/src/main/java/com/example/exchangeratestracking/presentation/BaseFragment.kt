package com.example.exchangeratestracking.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    abstract val layout: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var v: View? = view
        if (v == null) {
            v = inflater.inflate(layout, container, false)
        }

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onSetupLayout()
        onCollectFlow()
    }

    abstract fun onSetupLayout()

    abstract fun onCollectFlow()

}