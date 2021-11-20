package com.khaled.weatherapp.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

object ImageUtils {

    fun loadImage(context: Context, icon: String, resId: Int, imageView: ImageView) {
        val iconUrl = "http://openweathermap.org/img/w/$icon.png";
        Glide.with(context).load(iconUrl).placeholder(resId).into(imageView)
    }
}