package com.example.exchangeratestracking.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.exchangeratestracking.R
import com.example.exchangeratestracking.databinding.ItemExchangeRateBinding
import com.example.exchangeratestracking.presentation.entity.ExchangeRate
import kotlinx.android.synthetic.main.item_exchange_rate.view.*

class ExchangeRatesAdapter(private val onItemClick: (currency: String) -> Unit) :
    RecyclerView.Adapter<ExchangeRatesAdapter.HomeHolder>() {

    var favRates: List<String> = emptyList()
        set(newExchangeRateList) {
            field = newExchangeRateList
            notifyDataSetChanged()
        }

    var exchangeRates: List<ExchangeRate> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(newExchangeRateList) {
            field = newExchangeRateList
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        return HomeHolder(parent = parent)
    }

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        holder.onBind(exchangeRate = exchangeRates[position])
    }

    override fun getItemCount(): Int {
        return exchangeRates.size
    }

    private fun changeFavImg(view: ImageView, isFav: Boolean) {
        if (isFav) {
            view.setImageResource(R.drawable.ic_star_border)
        } else {
            view.setImageResource(R.drawable.ic_star)
        }
    }

    private fun isFav(currency: String): Boolean {
        return currency in favRates
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
            itemView.fav_iv.setOnClickListener { view ->
                this@ExchangeRatesAdapter.onItemClick(exchangeRate.currency)
            }
        }

        fun onBind(exchangeRate: ExchangeRate) = with(binding) {
            this@HomeHolder.exchangeRate = exchangeRate

            currencyTv.text = exchangeRate.currency
            valueTv.text = exchangeRate.value.toString()
            changeFavImg(favIv, isFav(exchangeRate.currency))
            if (!isFav(exchangeRate.currency)) {
                favIv.setImageResource(R.drawable.ic_star_border)
            } else {
                favIv.setImageResource(R.drawable.ic_star)
            }
        }
    }
}