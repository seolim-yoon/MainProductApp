package com.example.mainproductapp.repository

import com.example.mainproductapp.server.api.SectionApi
import com.example.mainproductapp.server.response.SectionData
import com.example.mainproductapp.server.response.SectionProductData
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import javax.inject.Inject

class SectionRepositoryImpl @Inject constructor(private val sectionApi: SectionApi) : SectionRepository {
    override fun getSectionList(page: Int): Single<SectionData> = sectionApi.getSectionList(page)
    override fun getSectionProductList(sectionId: Int): Single<SectionProductData> = sectionApi.getSectionProductList(sectionId)
}