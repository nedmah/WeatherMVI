package com.example.weathermvi.domain.weather

data class WeatherInfo (
    val weatherDataPerDay : Map<Int, List<WeatherData>>,
    val currentWeather : WeatherData?
)