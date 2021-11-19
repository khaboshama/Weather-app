package com.khaled.weatherapp.common

import android.app.Activity
import android.view.View
import android.widget.Toast
import java.lang.reflect.ParameterizedType

interface BaseView<ViewModel : BaseViewModel> {

    val viewModel: ViewModel
    val loadingView: View?

    fun showErrorMessage(errorMessage: String) {
        Toast.makeText(getCurrentActivity(), errorMessage, Toast.LENGTH_SHORT).show()
    }

    fun getCurrentActivity(): Activity

    @Suppress("UNCHECKED_CAST")
    fun viewModelClass() =
        ((javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<ViewModel>).kotlin

    fun showLoading() {
        loadingView?.visibility = View.VISIBLE
    }

    fun hideLoading() {
        loadingView?.visibility = View.GONE
    }

}