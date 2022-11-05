package com.example.mainproductapp.ui.holder

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mainproductapp.databinding.ItemSectionBinding
import com.example.mainproductapp.ui.adapter.ProductListAdapter
import com.example.mainproductapp.ui.model.SectionModel
import com.example.mainproductapp.util.SectionType

class SectionViewHolder(private val binding: ItemSectionBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(section: SectionModel) {
        binding.section = section

        ProductListAdapter().apply {
            binding.rvSectionList.adapter = this
            addProductList(section.productList)
            setViewType(section.type)
        }

        when (section.type) {
            SectionType.VERTICAL.type -> {
                val layoutManager = LinearLayoutManager(itemView.context)
                layoutManager.orientation = LinearLayoutManager.VERTICAL
                binding.rvSectionList.layoutManager = layoutManager
            }
            SectionType.HORIZONTAL.type -> {
                val layoutManager = LinearLayoutManager(itemView.context)
                layoutManager.orientation = LinearLayoutManager.HORIZONTAL
                binding.rvSectionList.layoutManager = layoutManager
            }
            SectionType.GRID.type -> {
                val layoutManager = GridLayoutManager(itemView.context, 3)
                binding.rvSectionList.layoutManager = layoutManager
            }
        }
    }
}