package com.example.exchangeratestracking.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.exchangeratestracking.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val adapter = HomeAdapter(
        onItemClick = { exchangeRate ->
//            startActivity(
//                WeatherDetailsActivity.getIntent(
//                    context = this,
//                    cityId = cityWeather.id,
//                    cityName = cityWeather.cityName
//                )
//            )
//            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
//        homeViewModel.exchangeRates.collect(viewLifecycleOwner) {
//            textView.text = it
//        }
        initRecyclerView()
        return root
    }


    private fun initRecyclerView() {
//        binding.
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}