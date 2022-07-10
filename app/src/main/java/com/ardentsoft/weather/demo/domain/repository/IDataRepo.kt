package com.ardentsoft.weather.demo.domain.repository

import com.ardentsoft.weather.demo.data.remote.response.WeatherDataApiResponse
import io.reactivex.Observable

interface IDataRepo {
    fun getCityDetails(): Observable<List<String>>
    fun getCityWeatherDetails(request: String): Observable<WeatherDataApiResponse>
}




