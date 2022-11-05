package com.example.mainproductapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mainproductapp.databinding.ItemOneLineBinding
import com.example.mainproductapp.databinding.ItemTwoLineBinding
import com.example.mainproductapp.ui.holder.OneLineProductViewHolder
import com.example.mainproductapp.ui.holder.TwoLineProductViewHolder
import com.example.mainproductapp.ui.model.ProductModel
import com.example.mainproductapp.util.SectionType

class ProductListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val itemList: MutableList<ProductModel> = mutableListOf()
    private var viewType = SectionType.VERTICAL.id
    private val onItemClicked = { position: Int ->
        itemList[position].isWishItem = itemList[position].isWishItem.not()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            SectionType.HORIZONTAL.id, SectionType.GRID.id -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemTwoLineBinding.inflate(layoutInflater, parent, false)
                TwoLineProductViewHolder(binding, onItemClicked)
            }
            else -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemOneLineBinding.inflate(layoutInflater, parent, false)
                OneLineProductViewHolder(binding, onItemClicked)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is OneLineProductViewHolder -> holder.bind(itemList[position])
            is TwoLineProductViewHolder -> holder.bind(itemList[position])
        }
    }

    override fun getItemCount(): Int = itemList.size

    override fun getItemViewType(position: Int): Int = viewType

    fun setViewType(type: String) {
        viewType = when (type) {
            SectionType.VERTICAL.type -> SectionType.VERTICAL.id
            SectionType.HORIZONTAL.type -> SectionType.HORIZONTAL.id
            SectionType.GRID.type -> SectionType.GRID.id
            else -> SectionType.VERTICAL.id
        }
        notifyDataSetChanged()
    }


    fun addProductList(nextProductList: List<ProductModel>) {
        itemList.addAll(nextProductList)
        notifyDataSetChanged()
    }

    fun clearProductList() {
        itemList.clear()
        notifyDataSetChanged()
    }
}