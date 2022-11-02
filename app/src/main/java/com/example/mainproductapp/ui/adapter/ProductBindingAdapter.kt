package com.example.mainproductapp.ui.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.mainproductapp.R
import com.example.mainproductapp.util.DecimalFormat

object ProductBindingAdapter {
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(ivImage: ImageView, url: String) {
        Glide.with(ivImage.context)
            .load(url)
            .error(R.drawable.baseline_error_24)
            .transition(DrawableTransitionOptions().crossFade())
            .fitCenter()
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
}