package com.example.weathermvi.data.repository

import com.example.weathermvi.data.mappers.toWeatherInfo
import com.example.weathermvi.data.remote.WeatherApi
import com.example.weathermvi.domain.repository.WeatherRepository
import com.example.weathermvi.domain.util.Resource
import com.example.weathermvi.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi
): WeatherRepository {
    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = weatherApi.getWeatherData(lat, long).toWeatherInfo()
            )
        } catch (e : Exception){
            Resource.Error(message = e.message ?: "Unknown error has occurred")
        }
    }
}