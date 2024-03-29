package com.muhammetkdr.weatherapp.data.remote.weather

import com.muhammetkdr.weatherapp.common.utils.Resource
import com.muhammetkdr.weatherapp.data.api.weather.WeatherAPIService
import com.muhammetkdr.weatherapp.data.dto.current.WeatherResponse
import com.muhammetkdr.weatherapp.data.dto.forecast.ForecastResponse
import com.muhammetkdr.weatherapp.di.Dispatcher
import com.muhammetkdr.weatherapp.di.DispatcherType
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WeatherRemoteDataSourceImpl @Inject constructor(
    private val api: WeatherAPIService,
    @Dispatcher(DispatcherType.Io) private val ioDispatcher: CoroutineDispatcher
) : WeatherRemoteDataSource {

    override fun getCurrentWeather(lat: String, long: String): Flow<Resource<WeatherResponse>> =
        flow {
            emit(Resource.Loading)
            val response = api.getCurrentWeather(latitude = lat, longitude = long)
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(Resource.Success(it))
                } ?: emit(Resource.Error(NO_DATA))
            } else {
                emit(Resource.Error(NO_DATA))
            }
        }.catch { emit(Resource.Error(it.localizedMessage ?: SOMETHING_BAD_HAPPENED)) }
            .flowOn(ioDispatcher)

    override fun getForecastWeather(lat: String, long: String): Flow<Resource<ForecastResponse>> =
        flow {
            emit(Resource.Loading)
            val response = api.getForecastWeather(latitude = lat, longitude = long)
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(Resource.Success(it))
                } ?: emit(Resource.Error(NO_DATA))
            } else {
                emit(Resource.Error(NO_DATA))
            }
        }.catch { emit(Resource.Error(it.localizedMessage ?: SOMETHING_BAD_HAPPENED)) }
            .flowOn(ioDispatcher)

    companion object {
        private const val NO_DATA = "No Data!"
        private const val SOMETHING_BAD_HAPPENED = "Something bad happened.."
    }
}
