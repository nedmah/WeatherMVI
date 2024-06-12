package com.example.weathermvi.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun weatherForecast(
    state: WeatherState,
    modifier: Modifier = Modifier
){

    state.weatherInfo?.weatherDataPerDay?.get(0)?.let {data ->

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Today",
            fontSize = 20.sp,
            color = Color.White,
            modifier = modifier.align(Alignment.End)
        )
        Spacer(modifier = modifier.size(20.dp))
        LazyRow(
            modifier = modifier.padding(16.dp)
        ){
            items(data){weatherData->
                weatherForecastItem(
                    weatherData = weatherData,
                    modifier = Modifier
                        .height(100.dp)
                        .padding(horizontal = 16.dp)
                )
            }
        }
    }

    }
}