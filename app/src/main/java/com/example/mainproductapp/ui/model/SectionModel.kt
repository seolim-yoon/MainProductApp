package com.example.mainproductapp.ui.model

data class SectionModel(
    val id: Int,
    val title: String,
    val type: String
) {
    var productList: List<ProductModel> = listOf()
}
