package com.example.mainproductapp.ui.model.mapper

import com.example.mainproductapp.server.response.SectionData
import com.example.mainproductapp.ui.model.SectionModel
import com.example.mainproductapp.util.Mapper
import com.example.mainproductapp.util.SectionType

class SectionDataMapper : Mapper<SectionData.Section, SectionModel?> {

    override fun map(section: SectionData.Section): SectionModel =
        SectionModel(
            section.id ?: 1,
            section.title ?: "",
            section.type ?: SectionType.VERTICAL.type
        )
}
