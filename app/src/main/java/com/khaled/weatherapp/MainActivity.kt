package com.khaled.weatherapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import com.khaled.weatherapp.common.BaseActivity

class MainActivity : BaseActivity<MainViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main)
    }

    override val loadingView: View?
        get() = null

    override fun getCurrentActivity() = this

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}