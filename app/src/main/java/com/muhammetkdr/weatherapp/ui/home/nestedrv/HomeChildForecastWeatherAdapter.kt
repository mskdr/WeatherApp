package com.muhammetkdr.weatherapp.ui.home.nestedrv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muhammetkdr.weatherapp.base.BaseListAdapter
import com.muhammetkdr.weatherapp.databinding.ItemChildWeatherDaysBinding
import com.muhammetkdr.weatherapp.domain.entity.forecastweather.forecastuidata.ChildRvUiData
import javax.inject.Inject

class HomeChildForecastWeatherAdapter @Inject constructor(
) : BaseListAdapter<ChildRvUiData>(
        itemsSame = { old, new -> old == new },
        contentsSame = { old, new -> old == new }) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val binding = ItemChildWeatherDaysBinding.inflate(inflater, parent, false)
        return HomeChildForecastWeatherViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HomeChildForecastWeatherViewHolder -> {
                getItem(position)?.let { item ->
                    holder.onBind(item)
                }
            }
        }
    }
}