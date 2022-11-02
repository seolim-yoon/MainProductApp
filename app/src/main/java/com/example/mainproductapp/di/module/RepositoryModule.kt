package com.example.mainproductapp.di.module

import com.example.mainproductapp.repository.SectionRepository
import com.example.mainproductapp.repository.SectionRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
interface RepositoryModule {

    @Binds
    fun bindSectionRepository(SectionRepositoryImpl: SectionRepositoryImpl): SectionRepository
}