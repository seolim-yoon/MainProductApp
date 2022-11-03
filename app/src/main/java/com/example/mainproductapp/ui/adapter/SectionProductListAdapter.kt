package com.example.mainproductapp.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mainproductapp.databinding.ItemOneLineBinding
import com.example.mainproductapp.databinding.ItemSectionVerticalBinding
import com.example.mainproductapp.databinding.ItemTwoLineBinding
import com.example.mainproductapp.ui.holder.OneLineProductViewHolder
import com.example.mainproductapp.ui.holder.SectionVerticalViewHolder
import com.example.mainproductapp.ui.holder.TwoLineProductViewHolder
import com.example.mainproductapp.ui.model.BaseModel
import com.example.mainproductapp.ui.model.ProductModel
import com.example.mainproductapp.ui.model.SectionModel
import com.example.mainproductapp.util.SectionType

class SectionProductListAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val itemList: MutableList<BaseModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.i("seolim", "viewType : " + viewType)
        return when (viewType) {
            SectionType.SECTION.id -> {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemSectionVerticalBinding.inflate(layoutInflater, parent, false)
                SectionVerticalViewHolder(binding)
            }
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

    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SectionVerticalViewHolder -> holder.bind(itemList[position] as SectionModel)
            is OneLineProductViewHolder -> holder.bind(itemList[position] as ProductModel)
            is TwoLineProductViewHolder -> holder.bind(itemList[position] as ProductModel)
        }
    }

    override fun getItemCount(): Int = itemList.size

    override fun getItemViewType(position: Int): Int = when(itemList[position]) {
        is SectionModel -> SectionType.SECTION.id
        is ProductModel -> {
            when((itemList[position] as ProductModel).viewType) {
                SectionType.VERTICAL.type -> SectionType.VERTICAL.id
                SectionType.HORIZONTAL.type -> SectionType.HORIZONTAL.id
                SectionType.GRID.type -> SectionType.GRID.id
                else -> SectionType.VERTICAL.id
            }
        }
        else -> SectionType.VERTICAL.id
    }

    fun addSectionList(nextSection: SectionModel) {
        itemList.add(nextSection)
        notifyDataSetChanged()
    }

    fun clearSectionList() {
        itemList.clear()
        notifyDataSetChanged()
    }

    fun addProductList(nextProductList: MutableList<ProductModel>) {
        itemList.addAll(nextProductList)
        notifyDataSetChanged()
    }

    fun clearProductList() {
        itemList.clear()
        notifyDataSetChanged()
    }
}