package com.example.mainproductapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mainproductapp.databinding.ItemSectionBinding
import com.example.mainproductapp.ui.holder.SectionViewHolder
import com.example.mainproductapp.ui.model.SectionModel

class SectionListAdapter() : RecyclerView.Adapter<SectionViewHolder>() {
    private val itemList: MutableList<SectionModel> = mutableListOf()

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

    fun addSectionList(nextSection: SectionModel) {
        itemList.add(nextSection)
        notifyItemRangeInserted(nextSection.id, 1)
    }

    fun clearSectionList() {
        itemList.clear()
        notifyDataSetChanged()
    }
}