package com.example.mainproductapp.repository

import com.example.mainproductapp.server.response.SectionData
import com.example.mainproductapp.server.response.SectionProductData
import io.reactivex.rxjava3.core.Single

interface SectionRepository {
    fun getSectionList(page: Int): Single<SectionData>
    fun getSectionProductList(sectionId: Int): Single<SectionProductData>
}