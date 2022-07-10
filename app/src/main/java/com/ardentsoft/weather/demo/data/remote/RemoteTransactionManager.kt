package com.ardentsoft.weather.demo.data.remote

import com.ardentsoft.weather.demo.BuildConfig
import com.ardentsoft.weather.demo.data.datasource.IRemoteDataTransaction
import com.ardentsoft.weather.demo.data.remote.api.IRemoteServiceApi
import com.ardentsoft.weather.demo.data.remote.response.WeatherDataApiResponse
import com.ardentsoft.weather.demo.netowrkservice.INetworkClientService
import io.reactivex.Observable
import javax.inject.Inject

class RemoteTransactionManager @Inject constructor(
    private val networkService: INetworkClientService
) :
    IRemoteDataTransaction {
    val listOfCity = listOf<String>(
        "Bangalore",
        "Chennai",
        "Hyderabad",
        "New Delhi",
        "Pune",
        "Mumbai",
        "Indore",
        "Gurgaon",
        "Kalyann"
    )
    override fun getCityList(): Observable<List<String>> {
       return Observable.just(listOfCity)
    }

    override fun getCityWeatherDetails(request: String): Observable<WeatherDataApiResponse> {
        return Observable.create<WeatherDataApiResponse> { emitter ->
            val call = getApiService().getWeatherDetails(request, BuildConfig.appId)
            val callback = networkService.getJsonCallback(emitter, WeatherDataApiResponse::class.java)
            call.enqueue(callback)
        }.flatMap { result ->
            Observable.just(result)
        }
    }

    private fun getApiService(): IRemoteServiceApi {
        return networkService.getNetworkClient(IRemoteServiceApi::class.java)
    }

}

