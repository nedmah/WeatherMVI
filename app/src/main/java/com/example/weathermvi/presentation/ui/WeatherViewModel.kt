package com.example.weathermvi.presentation.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathermvi.domain.Location.LocationTracker
import com.example.weathermvi.domain.repository.WeatherRepository
import com.example.weathermvi.domain.util.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val locationTracker: LocationTracker
): ViewModel() {

    var state = mutableStateOf(WeatherState())
        private set

    fun loadWeather() = viewModelScope.launch {
        state.value = state.value.copy(
            loading = true,
            error = null
        )

        locationTracker.getCurrentLocation()?.let {
            when(val result = weatherRepository.getWeatherData(it.latitude, it.longitude)){
                is Resource.Success -> {
                    state.value = state.value.copy(
                        loading = false,
                        error = null,
                        weatherInfo = result.data
                    )
                }
                is Resource.Error -> {
                    state.value = state.value.copy(
                        loading = false,
                        weatherInfo = null,
                        error = result.message
                    )
                }
                else -> {

                }
            }
        } ?: kotlin.run {
            state.value = state.value.copy(
                loading = false,
                error = "Couldn't retrieve location. Try to enable gps."
            )
        }
    }

}