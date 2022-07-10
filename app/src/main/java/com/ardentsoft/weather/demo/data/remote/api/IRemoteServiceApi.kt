package com.ardentsoft.weather.demo.data.remote.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IRemoteServiceApi {
    //https://api.openweathermap.org/data/2.5/weather?q=hyderabad&appid=a567eca4b52f20847650b5f73af13af9
    @GET("/data/2.5/weather")
    fun getWeatherDetails(@Query("q", encoded = true) cityName: String, @Query("appid", encoded = true) appid: String): Call<ResponseBody>
}