package com.example.weathermvi.domain.weather

import java.time.LocalDateTime

/** Weather domain model
 * representing weather for current hour
 */
data class WeatherData (
    val time : LocalDateTime,
    val temperature : Double,
    val pressure : Double,
    val windSpeed : Double,
    val humidity : Double,
    val weatherType : WeatherType
)