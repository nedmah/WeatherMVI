package com.example.weathermvi.presentation.ui

import com.example.weathermvi.domain.weather.WeatherInfo

data class WeatherState(
    val loading : Boolean = false,
    val error : String? = null,
    val weatherInfo: WeatherInfo? = null
)
