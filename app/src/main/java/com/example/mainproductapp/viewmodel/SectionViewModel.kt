package com.example.mainproductapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mainproductapp.repository.SectionRepositoryImpl
import com.example.mainproductapp.server.response.SectionData
import com.example.mainproductapp.server.response.SectionProductData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SectionViewModel @Inject constructor(private val repositoryImpl: SectionRepositoryImpl) :
    BaseViewModel() {

    private val _sectionDataLiveData = MutableLiveData<SectionData>()
    val sectionDataLiveData: LiveData<SectionData>
        get() = _sectionDataLiveData

    private val _sectionProductDataLiveData = MutableLiveData<Pair<SectionData.Section, SectionProductData>>()
    val sectionProductDataLiveData: LiveData<Pair<SectionData.Section, SectionProductData>>
        get() = _sectionProductDataLiveData

    fun getSectionProductList(page: Int) {
        val observableSection = repositoryImpl.getSectionList(page)
            .map { sectionData ->
                _sectionDataLiveData.postValue(sectionData)
                sectionData.data
            }
            .flatMapObservable { sectionList -> Observable.fromIterable(sectionList) }

        val observableProduct = observableSection
            .concatMap { section ->
                repositoryImpl.getSectionProductList(section.id ?: 1).toObservable()
            }

        addDisposable(
            Observable.zip(observableSection, observableProduct) { section, product ->
                Pair<SectionData.Section, SectionProductData>(section, product)
            }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ sectionProductData ->
                    _sectionProductDataLiveData.value = sectionProductData
                }, { e ->
                    Log.e("seolim", "error : " + e.message)
                })
        )
    }
}