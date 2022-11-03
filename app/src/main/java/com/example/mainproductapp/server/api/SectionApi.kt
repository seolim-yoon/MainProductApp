package com.example.mainproductapp.server.api

import com.example.mainproductapp.server.response.SectionData
import com.example.mainproductapp.server.response.SectionProductData
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SectionApi {
    @GET("sections")
    fun getSectionList(
        @Query("page") page: Int
    ): Single<SectionData>

    @GET("section/products")
    fun getSectionProductList(
        @Query("sectionId") sectionId: Int
    ): Single<SectionProductData>
}