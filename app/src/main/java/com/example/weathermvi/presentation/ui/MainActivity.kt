package com.example.weathermvi.presentation.ui

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weathermvi.presentation.ui.theme.WeatherMVITheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val weatherViewModel by viewModels<WeatherViewModel>()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
                weatherViewModel.loadWeather()
            }
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
        setContent {
            WeatherMVITheme {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {

                    if(weatherViewModel.state.value.loading) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                    weatherViewModel.state.value.error?.let { error->
                        Text(
                            text = error,
                            modifier = Modifier.align(Alignment.Center),
                            fontSize = 24.sp,
                            color = Color.White
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Gray)
                    ) {
                        weatherCard(
                            state = weatherViewModel.state.value,
                            modifier = Modifier,
                            backgroundColor = Color.DarkGray
                        )
                        Spacer(modifier = Modifier.size(16.dp))
                        weatherForecast(state = weatherViewModel.state.value)
                    }
                }
            }
        }
    }
}
