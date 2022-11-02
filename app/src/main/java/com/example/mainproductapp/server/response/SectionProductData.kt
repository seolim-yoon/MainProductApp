package com.example.mainproductapp.server.response


import com.google.gson.annotations.SerializedName

data class SectionProductData(
    @SerializedName("data")
    val `data`: List<SectionProduct>?
) {
    data class SectionProduct(
        @SerializedName("discountedPrice")
        val discountedPrice: Int?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("image")
        val image: String?,
        @SerializedName("isSoldOut")
        val isSoldOut: Boolean?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("originalPrice")
        val originalPrice: Int?
    )
}