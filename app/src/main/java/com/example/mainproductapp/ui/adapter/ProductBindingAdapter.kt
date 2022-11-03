package com.example.mainproductapp.ui.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.mainproductapp.R
import com.example.mainproductapp.util.DecimalFormat
import com.example.mainproductapp.util.SectionType

object ProductBindingAdapter {
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(ivImage: ImageView, url: String) {
        Glide.with(ivImage.context)
            .load(url)
            .error(R.drawable.baseline_error_24)
            .transition(DrawableTransitionOptions().crossFade())
            .centerCrop()
            .timeout(2000)
            .into(ivImage)
    }

    @BindingAdapter("productPrice")
    @JvmStatic
    fun setProductPrice(textView: TextView, price: Int?) {
        price?.let {
            textView.text = DecimalFormat.decimalFormat.format(it).toString() + "원"
        }
    }

    @BindingAdapter("viewType")
    @JvmStatic
    fun setViewType(recyclerView: RecyclerView, type: String?) {
        type?.let {
            when(it) {
                SectionType.VERTICAL.type -> {
                    val layoutManager = LinearLayoutManager(recyclerView.context)
                    layoutManager.orientation = LinearLayoutManager.VERTICAL
                    recyclerView.layoutManager = layoutManager
                }
                SectionType.HORIZONTAL.type -> {
                    val layoutManager = LinearLayoutManager(recyclerView.context)
                    layoutManager.orientation = LinearLayoutManager.HORIZONTAL
                    recyclerView.layoutManager = layoutManager
                }
                SectionType.GRID.type -> {
                    val layoutManager = GridLayoutManager(recyclerView.context, 3)
                    recyclerView.layoutManager = layoutManager
                }
            }
        }
    }
}