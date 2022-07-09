package com.ardentsoft.weather.demo.netowrkservice.response

import com.ardentsoft.weather.demo.netowrkservice.exception.IErrorResponse
import com.google.gson.annotations.Expose
import java.io.Serializable

class ErrorResponseInJsonImpl : IErrorResponse, Serializable {

    @Expose
    var cod: Int = 500

    @Expose
    var message: String? = null

    override fun getStatusCode(): Int {
        return cod
    }

    override fun getErrorMessage(): String? {
        return message
    }

}
