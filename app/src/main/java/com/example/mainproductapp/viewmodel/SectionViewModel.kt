package com.example.mainproductapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mainproductapp.repository.SectionRepository
import com.example.mainproductapp.server.response.SectionData
import com.example.mainproductapp.ui.model.SectionModel
import com.example.mainproductapp.ui.model.mapper.ProductDataMapper
import com.example.mainproductapp.ui.model.mapper.SectionDataMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SectionViewModel @Inject constructor(private val repository: SectionRepository) : BaseViewModel() {

    private val _sectionProductDataLiveData = MutableLiveData<SectionModel>()
    val sectionProductDataLiveData: LiveData<SectionModel>
        get() = _sectionProductDataLiveData

    var currentPage = 1
    var nextPage: Int? = null

    lateinit var section: SectionData.Section

    fun getSectionProductList(page: Int) {
        addDisposable(
            repository.getSectionList(page)
                .flatMapObservable { sectionData ->
                    nextPage = sectionData.paging?.nextPage
                    Observable.fromIterable(sectionData.data)
                }
                .concatMap { section ->
                    this.section = section
                    repository.getSectionProductList(section.id ?: 1).toObservable()
                }
                .map { productData ->
                    SectionDataMapper().map(section).apply {
                        productList = ProductDataMapper().map(productData) ?: listOf()
                    }
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ sectionModel ->
                    sectionModel.productList.forEach { product -> product.viewType = sectionModel.type }
                    _sectionProductDataLiveData.value = sectionModel
                }, { e ->
                    Log.e("seolim", "error : " + e.message)
                })
        )
    }

    fun refresh() {
        currentPage = 1
        nextPage = null
        clearDisposable()
        getSectionProductList(currentPage)
    }

    fun showNextPage() {
        nextPage?.let {
            currentPage = it
            getSectionProductList(currentPage)
        }
    }
}