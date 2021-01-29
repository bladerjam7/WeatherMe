package com.bladerco.weatherme.model

data class WeatherResult(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)