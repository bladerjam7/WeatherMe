package com.bladerco.weatherme.viewmodel

import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bladerco.weatherme.model.WeatherResult
import com.bladerco.weatherme.network.WeatherRetrofit
import com.bladerco.weatherme.util.Constant.Companion.TAG
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class WeatherViewModel : ViewModel() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val weatherLiveData: MutableLiveData<WeatherResult> = MutableLiveData()
    private val weatherRetrofit: WeatherRetrofit = WeatherRetrofit()


    fun getSearchResult(searchQuery: String) {
        compositeDisposable.add(
            weatherRetrofit.getSearchQuery(searchQuery)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe ({
                    Log.d(TAG, "getSearchResult: enter")
                    if(it != null) weatherLiveData.postValue(it)
                    compositeDisposable.clear()

                    Log.d("TAG", "getSearchResult: Success ")
                },{
                    Log.d("TAG", "getSearchResult: Failed $it")
                })
        )
    }
}