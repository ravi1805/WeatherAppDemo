package com.ardentsoft.weather.demo.netowrkservice

import io.reactivex.ObservableEmitter
import okhttp3.ResponseBody
import retrofit2.Callback

interface INetworkClientService {
    /**
     * Setup network client for application
     */
    fun setupNetworkClient(urlString: String)

    /**
     * Get network client
     */
    fun <T> getNetworkClient(apiServiceClass: Class<T>): T

    /**
     * Get json client callback
     * @param emitter
     * @param resCls response class
     */
    fun <R> getJsonCallback(emitter: ObservableEmitter<R>, resCls: Class<R>): Callback<ResponseBody>

    /**
     * Check whether internet is available or not
     */
    fun isMobileNetworkConnected(): Boolean
}