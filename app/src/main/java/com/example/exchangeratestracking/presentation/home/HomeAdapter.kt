package com.example.exchangeratestracking.presentation.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exchangeratestracking.databinding.ItemExchangeRateBinding
import com.example.exchangeratestracking.presentation.entity.ExchangeRate
import kotlinx.android.synthetic.main.item_exchange_rate.view.*

class HomeAdapter(private val onItemClick: (exchangeRate: ExchangeRate) -> Unit,
) : RecyclerView.Adapter<HomeAdapter.HomeHolder>() {

    var exchangeRateList: List<ExchangeRate> = emptyList()
    @SuppressLint("NotifyDataSetChanged")
    set(newExchangeRateList) {
        field = newExchangeRateList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        return HomeHolder(parent = parent)
    }

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        holder.onBind(exchangeRate = exchangeRateList[position])
    }

    override fun getItemCount(): Int {
        return exchangeRateList.size
    }

    inner class HomeHolder(
        parent: ViewGroup,
        private val binding: ItemExchangeRateBinding = ItemExchangeRateBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
    ) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var exchangeRate: ExchangeRate

        init {
            itemView.fav_iv.setOnClickListener {
                this@HomeAdapter.onItemClick(exchangeRate)
            }
        }

        fun onBind(exchangeRate: ExchangeRate) = with(binding) {
            this@HomeHolder.exchangeRate = exchangeRate

            currencyTv.text = exchangeRate.currency
            valueTv.text = exchangeRate.value.toString()
            //favIv
        }
    }

}