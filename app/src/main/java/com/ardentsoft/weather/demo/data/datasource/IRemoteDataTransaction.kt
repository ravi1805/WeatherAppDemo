package com.ardentsoft.weather.demo.data.datasource

import com.ardentsoft.weather.demo.data.remote.response.WeatherDataApiResponse
import io.reactivex.Observable

interface IRemoteDataTransaction {
    fun getCityList(): Observable<List<String>>
    fun getCityWeatherDetails(request: String): Observable<WeatherDataApiResponse>
}