package com.bladerco.weatherme.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.RecyclerView
import com.bladerco.weatherme.R
import com.bladerco.weatherme.util.Constant.Companion.TAG
import com.bladerco.weatherme.view.adapter.WeatherDaysAdapter
import com.bladerco.weatherme.viewmodel.WeatherViewModel
import com.bladerco.weatherme.viewmodel.WeatherViewModelFactory
import com.bumptech.glide.Glide


class MainActivity : AppCompatActivity() {

    private val viewModel: WeatherViewModel by viewModels<WeatherViewModel>(factoryProducer = {
        WeatherViewModelFactory
    })

    private var listFragment: ListFragment = ListFragment()

    private lateinit var tvCurrentTemp: TextView
    private lateinit var tvCityName : TextView
    private lateinit var imMainIcon : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvCurrentTemp = findViewById(R.id.tv_current_temp)
        tvCityName = findViewById(R.id.tv_city)
        imMainIcon = findViewById(R.id.iv_weather_icon)

        supportFragmentManager.beginTransaction()
                .add(R.id.fl_list_weather, listFragment)
                .commit()

        viewModel.weatherLiveData.observe(this, Observer {
            tvCurrentTemp.text = it.current.temp_f.toInt().toString()
            tvCityName.text = it.location.name

            Glide.with(this)
                .load("http:${it.current.condition.icon}")
                .into(imMainIcon)
        })
    }
}