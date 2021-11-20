package com.khaled.weatherapp.common

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
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

    fun checkAllPermissionGranted(grantResults: IntArray): Boolean {
        val permissionGranted = grantResults.filter { it == PackageManager.PERMISSION_GRANTED }
        return permissionGranted.size == grantResults.size
    }

    fun checkLocationPermissionIsNotGranted(context: Context) =
        ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
}