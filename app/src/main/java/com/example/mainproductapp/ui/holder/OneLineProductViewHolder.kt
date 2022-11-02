package com.example.mainproductapp.ui.holder

import androidx.recyclerview.widget.RecyclerView
import com.example.mainproductapp.databinding.ItemOneLineBinding
import com.example.mainproductapp.ui.model.ProductModel

class OneLineProductViewHolder(private val binding: ItemOneLineBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(product: ProductModel) {
        binding.product = product

    }
}