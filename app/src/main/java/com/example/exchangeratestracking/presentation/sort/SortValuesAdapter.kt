package com.example.exchangeratestracking.presentation.sort

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import com.example.exchangeratestracking.databinding.ItemFiltersValueBinding
import com.example.exchangeratestracking.presentation.entity.SortType

class SortValuesAdapter constructor(
    private val checkedSortType: SortType,
    private val onItemClick: (sort: SortType) -> Unit) :
    RecyclerView.Adapter<SortValuesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent = parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(sortType = SortType.values()[position])
    }

    override fun getItemCount(): Int {
        return SortType.values().size
    }

    inner class ViewHolder(private val parent: ViewGroup,
                     private val binding: ItemFiltersValueBinding = ItemFiltersValueBinding.inflate(
                         LayoutInflater.from(parent.context),
                         parent,
                         false
                     ),) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener { pressedItem ->
                if (!pressedItem.isSelected) {
                    parent.children.forEach { view ->
                        view.isSelected = false
                    }
                    pressedItem.isSelected = true
                    notifyDataSetChanged()
                    try {
                        this@SortValuesAdapter.onItemClick(SortType.values()[position])
                    } catch (exception: ClassCastException) {
                        Log.d("test", "${exception.message}")
                    }
                }
            }
        }

        fun onBind(sortType: SortType) = with(binding) {
            textViewValueTitle.text = parent.context.getString(sortType.titleRes)
            textViewValueTitle.isSelected = (sortType == checkedSortType)
        }
    }
}

//@Parcelize
//data class SortTypeListItem(
//    val id: Int,
//    var type: SortType,
//    var isChecked: Boolean
//) : Parcelable