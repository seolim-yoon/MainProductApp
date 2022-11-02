package com.example.mainproductapp.ui.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.mainproductapp.databinding.ItemTwoLineBinding
import com.example.mainproductapp.ui.model.ProductModel

class TwoLineProductViewHolder(private val binding: ItemTwoLineBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(product: ProductModel) {
        binding.product = product

    }
}