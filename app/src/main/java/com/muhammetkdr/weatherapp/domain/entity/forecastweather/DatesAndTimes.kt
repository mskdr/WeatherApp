package com.muhammetkdr.weatherapp.domain.entity.forecastweather

import com.muhammetkdr.weatherapp.data.dto.forecast.WeatherList

data class DatesAndTimes(val date: String,val dayOfTheWeek: String, val hours: List<String>, val list: List<WeatherList>){

}