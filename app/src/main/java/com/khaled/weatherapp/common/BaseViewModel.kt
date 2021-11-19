package com.khaled.weatherapp.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.khaled.weatherapp.common.data.AppResult
import com.khaled.weatherapp.common.data.HttpUtils
import com.khaled.weatherapp.di.AppContext
import com.khaled.weatherapp.utils.SingleLiveEvent
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    val error = SingleLiveEvent<String>()
    val showLoading = SingleLiveEvent<Boolean>()

    fun wrapBlockingOperation(loadingEnabled: Boolean = true, function: suspend () -> Any): Job {
        return viewModelScope.launch {
            try {
                showLoading.value = loadingEnabled
                function()
            } catch (throwable: Throwable) {
                HttpUtils.parseErrorResponse(throwable)
            } finally {
                showLoading.value = false
            }
        }
    }

    fun <T> handleResult(
        appResult: AppResult<T>? = null,
        onError: ((AppResult.Error) -> Unit)? = null,
        onSuccess: (AppResult.Success<T>) -> Unit
    ) {
        when (appResult) {
            is AppResult.Success<T> -> onSuccess(appResult)
            is AppResult.
            Error -> onError?.invoke(appResult)
        }
    }

    protected fun getErrorMessage(error: AppResult.Error) = if (error.errorMessageRes != null) {
        AppContext.applicationContext.context.getString(error.errorMessageRes)
    } else {
        error.errorMessage
    }

}