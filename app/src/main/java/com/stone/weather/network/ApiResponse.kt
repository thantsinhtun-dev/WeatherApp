package com.stone.weather.network

import com.stone.weather.model.ResponseStatus
import retrofit2.Response

sealed class ApiResponse<out T>(val status: ResponseStatus, val data: T?, val message: String?) {
    data class Success<out R>(val _data: R?) : ApiResponse<R>(
        status = ResponseStatus.SUCCESS,
        data = _data,
        message = null
    )

    data class Error<out R>(val error: String) : ApiResponse<R>(
        status = ResponseStatus.ERROR,
        data = null,
        message = error
    )
    data class NetWorkError<out R>(val error: String):ApiResponse<R>(
        status = ResponseStatus.NETWORK_ERROR,
        data = null,
        message = error
    )

}