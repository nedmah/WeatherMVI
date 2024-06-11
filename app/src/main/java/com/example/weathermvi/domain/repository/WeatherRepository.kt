package com.example.weathermvi.domain.repository

import com.example.weathermvi.domain.util.Resource
import com.example.weathermvi.domain.weather.WeatherInfo

interface WeatherRepository {

    suspend fun getWeatherData(lat: Double, long : Double) : Resource<WeatherInfo>
}