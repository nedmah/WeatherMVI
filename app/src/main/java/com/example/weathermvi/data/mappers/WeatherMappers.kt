package com.example.weathermvi.data.mappers

import com.example.weathermvi.data.remote.WeatherDataDto
import com.example.weathermvi.data.remote.WeatherDto
import com.example.weathermvi.domain.weather.WeatherData
import com.example.weathermvi.domain.weather.WeatherInfo
import com.example.weathermvi.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


private data class IndexedWeatherData(
    val index : Int,
    val weatherData: WeatherData
)


/**
 * this method maps out WeatherDataDto object to our domain WeatherData map
 * api returns lists with 7*24 elements, so we just divide them
 * our indexes are begin from 0 to 6 representing 7 days. 0 - today, 1 - tomorrow, etc
 */
fun WeatherDataDto.toWeatherDataMap() : Map<Int, List<WeatherData>>{
    return time.mapIndexed{ index, time ->
        val temperature = temperatures[index]
        val pressure = pressures[index]
        val windSpeed = windSpeeds[index]
        val humidity = humidities[index]
        val weatherType = weatherCodes[index]

        IndexedWeatherData(
            index,
            WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperature,
                pressure,
                windSpeed,
                humidity,
                WeatherType.fromWMO(weatherType)
            )
        )
    }.groupBy{
        it.index / 24
    }.mapValues {
        it.value.map {
            it.weatherData
        }
    }
}


/**
 * this method maps out WeatherDataDto object to WeatherInfo
 */
fun WeatherDto.toWeatherInfo() : WeatherInfo{
    val weatherDataMap = weatherData.toWeatherDataMap()
    var day = 0
    val now = LocalDateTime.now()

    val hour = when{
        now.minute < 30 -> now.hour
        (now.hour == 23 && now.minute > 30) -> 12
        else -> {now.hour+1}
    }.also {
        if(now.hour == 23 && now.minute > 30) day++
    }

    val currentWeatherData = weatherDataMap[0]?.find {
        it.time.hour == hour
    }
    return WeatherInfo(
        weatherDataMap,
        currentWeatherData
    )
}