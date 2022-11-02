package com.example.mainproductapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mainproductapp.repository.SectionRepositoryImpl
import com.example.mainproductapp.server.response.SectionData
import com.example.mainproductapp.server.response.SectionProductData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SectionViewModel @Inject constructor(private val repositoryImpl: SectionRepositoryImpl): BaseViewModel() {

    private val _sectionDataLiveData = MutableLiveData<SectionData>()
    val sectionDataLiveData: LiveData<SectionData>
        get() = _sectionDataLiveData

    private val _sectionProductDataLiveData = MutableLiveData<SectionProductData>()
    val sectionProductDataLiveData: LiveData<SectionProductData>
        get() = _sectionProductDataLiveData

    fun getSectionList(page: Int) {
        addDisposable(
            repositoryImpl.getSectionList(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ sectionData ->
                    _sectionDataLiveData.value = sectionData
                }, { e ->
                    Log.e("seolim", "error : " + e.message)
                })
        )
    }

    fun getSectionProductList(sectionId: Int) {
        addDisposable(
            repositoryImpl.getSectionProductList(sectionId)
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