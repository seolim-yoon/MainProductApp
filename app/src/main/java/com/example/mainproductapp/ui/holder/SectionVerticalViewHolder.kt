package com.example.mainproductapp.ui.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.mainproductapp.databinding.ItemSectionVerticalBinding
import com.example.mainproductapp.ui.model.SectionModel

class SectionVerticalViewHolder(private val binding: ItemSectionVerticalBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(section: SectionModel) {
        binding.section = section
    }
}