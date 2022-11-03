package com.example.mainproductapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mainproductapp.databinding.ItemSectionBinding
import com.example.mainproductapp.server.response.SectionData
import com.example.mainproductapp.server.response.SectionProductData
import com.example.mainproductapp.ui.holder.SectionViewHolder

class SectionListAdapter : RecyclerView.Adapter<SectionViewHolder>() {
    private val itemList: MutableList<Pair<SectionData.Section, SectionProductData>> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder =
        SectionViewHolder(
            ItemSectionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size

    fun addSectionList(nextSection: Pair<SectionData.Section, SectionProductData>) {
        itemList.add(nextSection)
        notifyDataSetChanged()
    }

    fun clearSectionList() {
        itemList.clear()
        notifyDataSetChanged()
    }
}