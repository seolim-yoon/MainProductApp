package com.example.mainproductapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mainproductapp.databinding.ItemSectionBinding
import com.example.mainproductapp.server.response.SectionData
import com.example.mainproductapp.ui.holder.SectionListViewHolder
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class SectionListAdapter @Inject constructor() : RecyclerView.Adapter<SectionListViewHolder>() {
    private val sectionItemList: MutableList<SectionData.Section> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionListViewHolder =
        SectionListViewHolder(
            ItemSectionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )

    override fun onBindViewHolder(holder: SectionListViewHolder, position: Int) {
        holder.bind(sectionItemList[position])
    }

    override fun getItemCount(): Int = sectionItemList.size

    fun addSectionList(nextSectionList: MutableList<SectionData.Section>) {
        sectionItemList.addAll(nextSectionList)
        notifyDataSetChanged()
    }

    fun clearSectionList() {
        sectionItemList.clear()
        notifyDataSetChanged()
    }
}