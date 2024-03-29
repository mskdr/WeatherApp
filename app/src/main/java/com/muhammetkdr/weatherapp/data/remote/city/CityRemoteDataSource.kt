package com.muhammetkdr.weatherapp.data.remote.city

import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.data.dto.city.CitiesResponse
import kotlinx.coroutines.flow.Flow

interface CityRemoteDataSource {
    fun getCityResponse() : Flow<Resource<List<CitiesResponse>>>
}