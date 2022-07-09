package com.ardentsoft.weather.demo.data.remote.response

/**
 * this the data model for parsing remote data
 */
data class WeatherDataApiResponse(val main: WeatherData)
data class WeatherData(val temp: String,val temp_min:String,val temp_max:String, val humidity:Int)


