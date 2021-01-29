package com.bladerco.weatherme.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bladerco.weatherme.R
import com.bladerco.weatherme.model.Forecast
import com.bladerco.weatherme.model.Forecastday
import com.bladerco.weatherme.model.Hour
import com.bladerco.weatherme.model.WeatherResult
import com.bladerco.weatherme.util.Constant.Companion.TAG
import com.bladerco.weatherme.view.adapter.WeatherDaysAdapter
import com.bladerco.weatherme.viewmodel.WeatherViewModel
import com.bladerco.weatherme.viewmodel.WeatherViewModelFactory
import kotlin.math.log

class ListFragment: Fragment() {

    private lateinit var weatherRV: RecyclerView
    private val adapter: WeatherDaysAdapter = WeatherDaysAdapter(mutableListOf())

    val hourList: MutableList<Hour> = mutableListOf()

    private val viewModel: WeatherViewModel by viewModels<WeatherViewModel>(factoryProducer = {
        WeatherViewModelFactory
    })

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.frag_days, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        weatherRV = view.findViewById(R.id.rv_list)

        Log.d(TAG, "onViewCreated: In Fragment")

        viewModel.weatherLiveData.observe(viewLifecycleOwner, Observer {
            it.forecast.forecastday.get(0).hour.forEach {
                hourList.add(it)
            }
            Log.d(TAG, "onViewCreated: In Live Data ")
            adapter.updateWeather(hourList)
        })

        viewModel.getSearchResult("Atlanta")
        weatherRV.adapter = adapter
    }
}