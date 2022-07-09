package com.ardentsoft.weather.demo.data.datasource

import com.ardentsoft.weather.demo.data.remote.response.WeatherDataApiResponse
import com.ardentsoft.weather.demo.domain.model.UIWeatherData
import io.reactivex.Observable
import io.reactivex.Single

interface IRemoteDataTransaction {
    fun getCityList(): Observable<List<String>>
    fun getCityWeatherDetails(request: String): Observable<WeatherDataApiResponse>
}