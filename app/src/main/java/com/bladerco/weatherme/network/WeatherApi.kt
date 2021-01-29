package com.bladerco.weatherme.network

import com.bladerco.weatherme.model.WeatherResult
import com.bladerco.weatherme.util.Constant.Companion.API_HEADER
import com.bladerco.weatherme.util.Constant.Companion.DAYS
//import com.bladerco.weatherme.util.Constant.Companion.HEADER
import com.bladerco.weatherme.util.Constant.Companion.PATH
import com.bladerco.weatherme.util.Constant.Companion.QUERY
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

   /* @GET(PATH)
    fun searchLocation(@Query(QUERY) searchQuery: String, @Query(API_HEADER) apiKey: String): Observable<Forecast>*/

    //@Headers(HEADER)
    @GET(PATH)
    fun searchLocation(@Query(QUERY) searchQuery: String, @Query(DAYS) daysQuery: String, @Query(API_HEADER) apiKey: String): Observable<WeatherResult>
}