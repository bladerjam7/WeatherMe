package com.bladerco.weatherme.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bladerco.weatherme.R
import com.bladerco.weatherme.model.Forecast
import com.bladerco.weatherme.model.Forecastday
import com.bladerco.weatherme.model.Hour
import com.bladerco.weatherme.util.Constant.Companion.TAG
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.text.SimpleDateFormat
import java.util.*

class WeatherDaysAdapter(private var hourList: List<Hour>) : RecyclerView.Adapter<WeatherDaysAdapter.WeatherViewHolder>(){

    private val pattern = "YYYY-MM-DD hh:mm"
    private lateinit var simpleDateFormat: SimpleDateFormat


    class WeatherViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val tvDayOfWeek: TextView = itemView.findViewById(R.id.tv_day_of_week)
        val tvTempHigh: TextView = itemView.findViewById(R.id.tv_day_high)
        val ivWeatherIcon: ImageView = itemView.findViewById(R.id.iv_day_weather_icon)
        val tvRainChance: TextView = itemView.findViewById(R.id.tv_percent_rain)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder =
            WeatherViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_days_layout, parent, false))

    override fun getItemCount(): Int {
        return hourList.size
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val hour = hourList.get(position)

        Log.d(TAG, "onBindViewHolder: ${hour.time}")

        simpleDateFormat = SimpleDateFormat(pattern)
        val newDate: Date = simpleDateFormat.parse(hour.time)
        simpleDateFormat = SimpleDateFormat("h:mm")
        val date = simpleDateFormat.format(newDate)

        holder.apply {
            tvDayOfWeek.text = date
            tvTempHigh.text = "High ${hour.temp_f}"
            tvRainChance.text = "${hour.chance_of_rain}"
            Glide.with(itemView.context)
                    .applyDefaultRequestOptions(RequestOptions().centerCrop())
                    .load("http:${hour.condition.icon}")
                    .into(ivWeatherIcon)
        }
    }

    fun updateWeather(weatherList: List<Hour>) {
        hourList = weatherList
        notifyDataSetChanged()
    }
}