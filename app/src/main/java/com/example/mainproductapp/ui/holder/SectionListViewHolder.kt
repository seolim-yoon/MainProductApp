package com.example.mainproductapp.ui.holder

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mainproductapp.databinding.ItemSectionBinding
import com.example.mainproductapp.server.response.SectionData
import com.example.mainproductapp.ui.adapter.ProductListAdapter
import com.example.mainproductapp.util.SectionType
import javax.inject.Inject

class SectionListViewHolder(private val binding: ItemSectionBinding): RecyclerView.ViewHolder(binding.root) {

    @Inject
    lateinit var productAdapter: ProductListAdapter

    fun bind(section: SectionData.Section) {
        binding.section = section

        when(section.type) {
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

//        binding.rvSectionList.adapter = productAdapter
    }
}