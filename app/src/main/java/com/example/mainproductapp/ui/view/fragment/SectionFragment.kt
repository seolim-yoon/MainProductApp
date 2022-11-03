package com.example.mainproductapp.ui.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mainproductapp.databinding.FragmentSectionBinding
import com.example.mainproductapp.ui.adapter.SectionProductListAdapter
import com.example.mainproductapp.ui.model.mapper.ProductDataMapper
import com.example.mainproductapp.ui.model.mapper.SectionDataMapper
import com.example.mainproductapp.viewmodel.SectionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SectionFragment : Fragment() {
    private var _viewBinding: FragmentSectionBinding? = null
    private val viewBinding get() = _viewBinding!!

    private val viewModel: SectionViewModel by viewModels()
    private var currentPage = 1
    private var nextPage: Int? = null

    private val sectionAdapter: SectionProductListAdapter by lazy {
        SectionProductListAdapter()
    }

    private val rvScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val lastVisibleItemPosition =
                (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
            val itemTotalCount = recyclerView.adapter!!.itemCount - 1

            if (lastVisibleItemPosition == itemTotalCount) {
                nextPage?.let {
                    currentPage = it
                    requestApi(currentPage)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentSectionBinding.inflate(inflater, container, false)
        initView()
        initObserver()
        requestApi(currentPage)
        return viewBinding.root
    }

    private fun initView() {
        with(viewBinding) {
            rvMainSectionList.adapter = sectionAdapter
//            rvMainSectionList.addOnScrollListener(rvScrollListener)
        }
    }

    private fun initObserver() {
        with(viewModel) {
//            sectionDataLiveData.observe(viewLifecycleOwner) { sectionData ->
//                Log.w("seolim", "sectionDataLiveData")
//                nextPage = sectionData.paging?.nextPage
//                sectionAdapter.addSectionList(SectionDataMapper().map(sectionData)?.toMutableList() ?: mutableListOf())
//            }

            sectionProductDataLiveData.observe(viewLifecycleOwner) { sectionProductData ->
                sectionAdapter.addSectionList(SectionDataMapper().map(sectionProductData.first))
                ProductDataMapper().map(sectionProductData.second)?.apply {
                    forEach { product -> product.viewType = sectionProductData.first.type }
                    sectionAdapter.addProductList(toMutableList())
                }
            }
        }
    }

    private fun requestApi(page: Int) {
        viewModel.getSectionProductList(page)
    }

}