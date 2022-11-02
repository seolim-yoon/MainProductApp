package com.example.mainproductapp.ui.model.mapper

import com.example.mainproductapp.server.response.SectionProductData
import com.example.mainproductapp.ui.model.ProductModel
import com.example.mainproductapp.util.Mapper

class ProductDataMapper : Mapper<SectionProductData, List<ProductModel>?> {

    override fun map(from: SectionProductData): List<ProductModel>? =
        from.data?.map { product ->
            ProductModel(
                product.id ?: 0,
                product.name ?: "",
                product.image ?: "",
                product.originalPrice ?: 0,
                product.discountedPrice,
                null,
                false
            )
        }
}