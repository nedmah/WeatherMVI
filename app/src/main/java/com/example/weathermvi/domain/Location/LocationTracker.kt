package com.example.weathermvi.domain.Location

import android.location.Location
import com.example.weathermvi.domain.util.Resource

interface LocationTracker {

    suspend fun getCurrentLocation(): Location?
}