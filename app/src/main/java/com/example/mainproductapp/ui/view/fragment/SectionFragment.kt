package com.example.mainproductapp.ui.view.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mainproductapp.databinding.FragmentSectionBinding
import com.example.mainproductapp.ui.adapter.SectionListAdapter
import com.example.mainproductapp.ui.model.mapper.ProductDataMapper
import com.example.mainproductapp.util.CustomDividerDecoration
import com.example.mainproductapp.viewmodel.SectionViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SectionFragment : Fragment() {
    private var _viewBinding: FragmentSectionBinding? = null
    private val viewBinding get() = _viewBinding!!

    private val viewModel: SectionViewModel by viewModels()
    private var currentPage = 1
    private var nextPage: Int? = null
    private var dataSize = 0

    private val sectionAdapter: SectionListAdapter by lazy {
        SectionListAdapter()
    }

    private val rvScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val lastVisibleItemPosition =
                (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
            val itemTotalCount = recyclerView.adapter!!.itemCount - 1

            if (lastVisibleItemPosition == itemTotalCount && dataSize * currentPage == sectionAdapter.itemCount) {
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

        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserver()
        requestApi(currentPage)

    }

    private fun initView() {
        with(viewBinding) {
            rvMainSectionList.addItemDecoration(CustomDividerDecoration(5f, 5f, Color.BLACK))
            rvMainSectionList.adapter = sectionAdapter
            rvMainSectionList.addOnScrollListener(rvScrollListener)
        }
    }

    private fun initObserver() {
        with(viewModel) {
            sectionDataLiveData.observe(viewLifecycleOwner) { sectionData ->
                nextPage = sectionData.paging?.nextPage
                dataSize = sectionData.data.size
            }

            sectionProductDataLiveData.observe(viewLifecycleOwner) { sectionProductData ->
                sectionAdapter.addSectionList(sectionProductData)
                ProductDataMapper().map(sectionProductData.second)
                    ?.forEach { product -> product.viewType = sectionProductData.first.type }
            }
        }
    }

    private fun requestApi(page: Int) {
        viewModel.getSectionProductList(page)
    }
}