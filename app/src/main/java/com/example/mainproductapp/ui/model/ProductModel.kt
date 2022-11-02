package com.example.mainproductapp.ui.model

data class ProductModel(
    val id: Int,
    val name: String,
    val imgUrl: String,
    val originalPrice: Int,
    val discountPrice: Int?,
    val viewType: Int?,
    val isWishItem: Boolean
)