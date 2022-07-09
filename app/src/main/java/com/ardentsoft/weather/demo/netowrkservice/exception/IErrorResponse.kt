package com.ardentsoft.weather.demo.netowrkservice.exception

interface IErrorResponse {
    fun getStatusCode(): Int
    fun getErrorMessage(): String?
}