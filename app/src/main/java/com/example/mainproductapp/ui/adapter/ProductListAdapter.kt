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
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class ProductListAdapter @Inject constructor() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val productItemList: MutableList<ProductModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            SectionType.HORIZONTAL.id or SectionType.GRID.id -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemTwoLineBinding.inflate(layoutInflater, parent, false)
                TwoLineProductViewHolder(binding)
            }
            else -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemOneLineBinding.inflate(layoutInflater, parent, false)
                OneLineProductViewHolder(binding)
            }
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {
            is OneLineProductViewHolder -> holder.bind(productItemList[position])
            is TwoLineProductViewHolder -> holder.bind(productItemList[position])
        }
    }

    override fun getItemCount(): Int = productItemList.size

    override fun getItemViewType(position: Int): Int =
        productItemList[position].viewType ?: SectionType.VERTICAL.id

    fun addProductList(nextProductList: MutableList<ProductModel>) {
        productItemList.addAll(nextProductList)
        notifyDataSetChanged()
    }

    fun clearProductList() {
        productItemList.clear()
        notifyDataSetChanged()
    }
}