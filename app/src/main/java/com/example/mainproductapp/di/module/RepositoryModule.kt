package com.example.mainproductapp.di.module

import com.example.mainproductapp.repository.SectionRepository
import com.example.mainproductapp.repository.SectionRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindSectionRepository(SectionRepositoryImpl: SectionRepositoryImpl): SectionRepository
}