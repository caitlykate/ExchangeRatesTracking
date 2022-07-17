package com.example.exchangeratestracking.presentation.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.exchangeratestracking.databinding.ItemSpinnerBinding

//interface String {
//
//    fun getItemTitle(): String
//
////    fun getItemId(): String
//
//    fun isSelected(selectedId: String?): Boolean {
//        return getItemTitle() == selectedId
//    }
//}

class SpinnerAdapter(
    private val items: List<String>
): BaseAdapter()  {
//
//   var items = listOf<String>()
//
//    var items = listOf("qwe","rty","rerrfg")

    var selectedItem: String? = null

    var onItemClick: (item: String) -> Unit = {}
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): String {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = convertView?.tag as ItemSpinnerBinding? ?: createBinding(parent.context)
        binding.textViewItemTitle.text = getItem(position)

        return binding.root
    }

    private fun createBinding(context: Context): ItemSpinnerBinding {
        val binding = ItemSpinnerBinding.inflate(LayoutInflater.from(context))
        binding.root.tag = binding
        return binding
    }

}