package com.ardentsoft.weather.demo.netowrkservice.exception

class CloudErrorThrowable(val error: IErrorResponse) : Throwable() {

    override fun getLocalizedMessage(): String? {
        return error.
        getErrorMessage()
    }

    override val message: String?
        get() = error.getErrorMessage()

}