package com.example.mainproductapp.server.response


import com.google.gson.annotations.SerializedName

data class SectionData(
    @SerializedName("data")
    val `data`: List<Section>,
    @SerializedName("paging")
    val paging: Paging?
) {
    data class Section(
        @SerializedName("id")
        val id: Int?,
        @SerializedName("title")
        val title: String?,
        @SerializedName("type")
        val type: String?,
        @SerializedName("url")
        val url: String?
    )

    data class Paging(
        @SerializedName("next_page")
        val nextPage: Int?
    )
}