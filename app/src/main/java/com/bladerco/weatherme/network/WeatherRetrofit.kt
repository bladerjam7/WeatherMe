package com.bladerco.weatherme.network

import android.util.Log
import com.bladerco.weatherme.model.WeatherResult
import com.bladerco.weatherme.util.Constant.Companion.API_KEY
import com.bladerco.weatherme.util.Constant.Companion.BASE_URL
import com.bladerco.weatherme.util.Constant.Companion.DAYS_NUMBER
import com.bladerco.weatherme.util.Constant.Companion.TAG
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class WeatherRetrofit{

    private var weatherApi: WeatherApi

    init{
        weatherApi = createWeatherApi(createRetrofit())
    }

    private fun createWeatherApi(retrofit: Retrofit): WeatherApi =
        retrofit.create(WeatherApi::class.java)


    private fun createRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    fun getSearchQuery(searchQuery: String): Observable<WeatherResult> {
        Log.d(TAG, "getSearchQuery: Working......")
        return weatherApi.searchLocation(searchQuery, DAYS_NUMBER, API_KEY)
    }
}