package com.example.mainproductapp.ui.view.fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.mainproductapp.R
import com.example.mainproductapp.databinding.FragmentSectionBinding
import com.example.mainproductapp.ui.adapter.SectionListAdapter
import com.example.mainproductapp.util.CustomDividerDecoration
import com.example.mainproductapp.viewmodel.SectionViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SectionFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {
    private var _viewBinding: FragmentSectionBinding? = null
    private val viewBinding get() = _viewBinding!!

    private val viewModel: SectionViewModel by viewModels()

    private val sectionAdapter: SectionListAdapter by lazy {
        SectionListAdapter()
    }

    private var refreshLayout: SwipeRefreshLayout? = null

    private val rvScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val lastVisibleItemPosition = (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastVisibleItemPosition()
            val itemTotalCount = recyclerView.adapter!!.itemCount - 1

            if (lastVisibleItemPosition == itemTotalCount && viewModel.dataSize * viewModel.currentPage == sectionAdapter.itemCount) {
                viewModel.showNextPage()
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
        requestApi(viewModel.currentPage)
    }

    private fun initView() {
        with(viewBinding.rvMainSectionList) {
            setHasFixedSize(true)
            isNestedScrollingEnabled = false
            adapter = sectionAdapter
            addItemDecoration(CustomDividerDecoration(5f, 5f, Color.BLACK))
            addOnScrollListener(rvScrollListener)
        }

        with(viewBinding.slRefresh) {
            refreshLayout = this
            initRefreshLayout(this)
        }
    }

    private fun initRefreshLayout(view: SwipeRefreshLayout) {
        view.setColorSchemeColors(ContextCompat.getColor(requireContext(), R.color.purple))
        view.setOnRefreshListener(this)
    }

    private fun initObserver() {
        with(viewModel) {
            sectionProductDataLiveData.observe(viewLifecycleOwner) { sectionModel ->
                refreshLayout?.isRefreshing = false
                sectionAdapter.addSectionList(sectionModel)
            }
        }
    }

    override fun onRefresh() {
        sectionAdapter.clearSectionList()
        viewModel.refresh()
    }

    private fun requestApi(page: Int) {
        viewModel.getSectionProductList(page)
    }
}