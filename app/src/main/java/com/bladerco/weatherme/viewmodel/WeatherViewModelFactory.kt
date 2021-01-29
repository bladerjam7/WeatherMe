package com.bladerco.weatherme.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

object WeatherViewModelFactory: ViewModelProvider.Factory{

    private var viewModel: WeatherViewModel? = WeatherViewModel()

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        viewModel?.let {
            return viewModel as T
        } ?: {
            viewModel = WeatherViewModel()
        }()

        return viewModel as T
    }

    fun destoryViewModel(){
        viewModel = null
    }


}