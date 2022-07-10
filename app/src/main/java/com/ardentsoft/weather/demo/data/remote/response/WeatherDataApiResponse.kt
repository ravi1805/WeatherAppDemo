package com.ardentsoft.weather.demo.data.remote.response

/**
 * this the data model for parsing remote data
 */
data class WeatherDataApiResponse(val main: WeatherData,val name:String)
data class WeatherData(val temp: String,val temp_min:String,val temp_max:String)


