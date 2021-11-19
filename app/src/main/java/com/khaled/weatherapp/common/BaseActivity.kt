package com.khaled.weatherapp.common

import androidx.appcompat.app.AppCompatActivity
import org.koin.android.ext.android.getKoin
import org.koin.androidx.viewmodel.ViewModelOwner
import org.koin.androidx.viewmodel.koin.getViewModel

abstract class BaseActivity<ViewModel : BaseViewModel> : AppCompatActivity(), BaseView<ViewModel> {

    override val viewModel by lazy {
        getKoin().getViewModel(
            owner = { ViewModelOwner.from(this) },
            clazz = viewModelClass()
        )
    }
}