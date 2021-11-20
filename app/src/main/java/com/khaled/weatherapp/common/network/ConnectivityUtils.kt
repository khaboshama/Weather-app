package com.khaled.weatherapp.common.network

import android.content.Context
import android.location.LocationManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.*
import android.os.Build
import com.khaled.weatherapp.di.AppContext.applicationContext

object ConnectivityUtils {

    @Suppress("DEPRECATION")
    fun isNetworkConnected(): Boolean {
        val cm = applicationContext.context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)
            capabilities != null && (capabilities.hasTransport(TRANSPORT_WIFI)
                    || capabilities.hasTransport(TRANSPORT_CELLULAR)
                    || capabilities.hasTransport(TRANSPORT_ETHERNET))
        } else {
            val activeNetwork = cm.activeNetworkInfo
            activeNetwork != null && activeNetwork.isConnected
        }
    }

    fun isGpsEnabled(): Boolean {
        val lm = applicationContext.context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return lm.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    fun isNetworkEnabled(): Boolean {
        val lm = applicationContext.context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

}