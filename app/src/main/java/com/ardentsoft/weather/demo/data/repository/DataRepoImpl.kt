package com.ardentsoft.weather.demo.data.repository

import com.ardentsoft.weather.demo.data.datasource.IRemoteDataTransaction
import com.ardentsoft.weather.demo.data.remote.response.WeatherDataApiResponse
import com.ardentsoft.weather.demo.domain.model.UIWeatherData
import com.ardentsoft.weather.demo.domain.repository.IDataRepo
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class DataRepoImpl @Inject constructor(
    private val remoteDataTransaction: IRemoteDataTransaction) :
    IDataRepo {

    override fun getCityWeatherDetails(request: String): Observable<WeatherDataApiResponse> {
        return remoteDataTransaction.getCityWeatherDetails(request)
    }

    override fun getCityDetails(): Observable<List<String>> {
        return remoteDataTransaction.getCityList()
    }


}