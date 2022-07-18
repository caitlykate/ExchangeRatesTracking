package com.example.exchangeratestracking.presentation.sort

import android.content.Context
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.exchangeratestracking.R
import com.example.exchangeratestracking.databinding.ItemFiltersValueBinding
import kotlinx.android.parcel.Parcelize


class SortValuesAdapter constructor(private var sortTypes: List<SortType>, private val onItemClick: (sort: Int) -> Unit) :
    RecyclerView.Adapter<SortValuesAdapter.ViewHolder>() {

    var checkedItem = 0
        set(value){
            changeSortTypesList(value, field)
            field = value
        }

    private fun changeSortTypesList(newSort: Int, oldSort: Int){
        sortTypes[oldSort].isChecked = false
        sortTypes[newSort].isChecked = true
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent = parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(sortType = sortTypes[position])
    }

    override fun getItemCount(): Int {
        return sortTypes.size
    }

    inner class ViewHolder(parent: ViewGroup,
                     private val binding: ItemFiltersValueBinding = ItemFiltersValueBinding.inflate(
                         LayoutInflater.from(parent.context),
                         parent,
                         false
                     ),) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var sortType: SortType

        init {
            itemView.setOnClickListener {
                if (checkedItem != position) {
                    checkedItem = position
                    notifyDataSetChanged()
                    try {
                        this@SortValuesAdapter.onItemClick(position)
                    } catch (exception: ClassCastException) {
                        Log.d("test", "${exception.message}")
                    }
                }
            }
        }

        fun onBind(sortType: SortType) = with(binding) {
            this@ViewHolder.sortType = sortType

            textViewValueTitle.text = sortType.text
            textViewValueTitle.isSelected = sortType.isChecked
        }
    }
}
//
//class FilterValuesAdapter(private val mAdapterCallback: AdapterCallback) : ListAdapter<CatalogDetailedFilterValue,
//                            FilterValuesAdapter.FilterValueViewHolder>(
//                                CatalogDetailedFilterValueDiffUtilItemCallback()
//) {
//
////    private val checkedItems = SparseBooleanArray()
//    private var checkedItem = 0
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterValueViewHolder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.item_filters_value, parent, false)
//        return FilterValueViewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: FilterValueViewHolder, position: Int) {
//        holder.bind(getItem(position))
//    }
//
//    override fun submitList(list: List<CatalogDetailedFilterValue>?) {
//        super.submitList(list)
//
//        list.orEmpty().forEachIndexed { position, value ->
////            checkedItems[position] = value.isChecked
//            if (value.isChecked) checkedItem = position
//        }
//        notifyDataSetChanged()
//    }
//
//    inner class FilterValueViewHolder(
//        itemView: View
//    ) : RecyclerView.ViewHolder(itemView) {
//
//        fun bind(item: CatalogDetailedFilterValue) = with(itemView) {
//
//            textViewValueTitle.text = item.text
////
////            textViewValueTitle.isSelected = checkedItems[adapterPosition]
//            if (adapterPosition == checkedItem)
//            textViewValueTitle.isSelected = true
//
//            layoutRoot.setOnClickListener(object : View.OnClickListener{
////                checkedItems.clear()
////                checkedItems[adapterPosition] = !checkedItems[adapterPosition]
//
//                override fun onClick(v: View?) {
//                    if (checkedItem != adapterPosition) {
//                        checkedItem = adapterPosition
//                        notifyDataSetChanged()
////                notifyItemChanged(adapterPosition)
//                        try {
//                            mAdapterCallback.onMethodCallback(adapterPosition)
//                        } catch (exception: ClassCastException) {
//                            Log.d("test", "${exception.message}")
//                        }
//                    }
//                }
//
//            })
//        }
//    }
//}

class CatalogDetailedFilterValueDiffUtilItemCallback :
    DiffUtil.ItemCallback<SortType>() {

    override fun areItemsTheSame(
        oldItem: SortType,
        newItem: SortType
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: SortType,
        newItem: SortType
    ): Boolean {
        return oldItem == newItem
    }
}

@Parcelize
data class SortType(
    val id: Int,
    var text: String,
    var isChecked: Boolean
) : Parcelable
//
//interface AdapterCallback {
//    fun onMethodCallback(sortBy: Int)
//}