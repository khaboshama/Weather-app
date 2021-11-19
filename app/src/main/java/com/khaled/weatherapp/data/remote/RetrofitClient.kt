package com.khaled.weatherapp.data.remote

import com.khaled.weatherapp.BuildConfig
import com.khaled.weatherapp.data.remote.endpoint.WeatherApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private const val CONNECT_TIMEOUT = 15L
    private const val READ_WRITE_TIMEOUT = 10L
    private const val BASE_URL = "https://api.openweathermap.org/"

    val weatherApi: WeatherApi by lazy { getRetrofit().create(WeatherApi::class.java) }

    private fun getRetrofit(): Retrofit {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(READ_WRITE_TIMEOUT, TimeUnit.MINUTES)
            .readTimeout(READ_WRITE_TIMEOUT, TimeUnit.MINUTES)
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }

        val client: OkHttpClient = builder.build()
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(client)
            .build()
    }
}
