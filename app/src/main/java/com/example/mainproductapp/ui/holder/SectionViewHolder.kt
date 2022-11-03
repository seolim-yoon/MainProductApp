package com.example.mainproductapp.ui.holder

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mainproductapp.databinding.ItemSectionBinding
import com.example.mainproductapp.server.response.SectionData
import com.example.mainproductapp.server.response.SectionProductData
import com.example.mainproductapp.ui.adapter.ProductListAdapter
import com.example.mainproductapp.ui.model.mapper.ProductDataMapper
import com.example.mainproductapp.ui.model.mapper.SectionDataMapper
import com.example.mainproductapp.util.SectionType

class SectionViewHolder(private val binding: ItemSectionBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(sectionProduct: Pair<SectionData.Section, SectionProductData>) {
        val section = SectionDataMapper().map(sectionProduct.first)
        binding.section = section
        binding.rvSectionList.adapter = ProductListAdapter()
        (binding.rvSectionList.adapter as ProductListAdapter).apply {
            addProductList(ProductDataMapper().map(sectionProduct.second)?.toMutableList() ?: mutableListOf())
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