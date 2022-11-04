package com.example.mainproductapp.ui.holder

import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import com.example.mainproductapp.databinding.ItemOneLineBinding
import com.example.mainproductapp.ui.model.ProductModel

class OneLineProductViewHolder(private val binding: ItemOneLineBinding, private val onItemClicked: (Int) -> Unit): RecyclerView.ViewHolder(binding.root) {
    init {
        binding.ivProductWish.setOnClickListener {
            onItemClicked(adapterPosition)
            binding.invalidateAll()
        }
    }

    fun bind(product: ProductModel) {
        with(binding) {
            this.product = product
            tvProductOriginalPrice.apply {
                paintFlags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            }
        }
    }
}