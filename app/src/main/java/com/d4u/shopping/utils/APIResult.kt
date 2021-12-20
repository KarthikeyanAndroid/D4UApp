package com.oviesmarterware.ovie.utils

import com.d4u.shopping.data.GetHomeProductModel

/**
 * A generic class that holds a value with its loading status.
 *
 * Result is usually created by the Repository classes where they return
 * `LiveData<Result<T>>` to pass back the latest data to the UI with its fetch status.
 */

data class APIResult<out T ,out T2,out T3>(val status: Status, val data: T?,val data2: T2?,val data3: T3?, val message: String?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T,T2,T3> success(data: T?,data2: T2?,data3:T3?): APIResult<T,T2,T3> {
            return APIResult(Status.SUCCESS, data, data2,data3,null)
        }

        fun <T,T2,T3> error(message: String, data: T?, data2: T2?,data3: T3?): APIResult<T,T2,T3> {
            return APIResult(Status.ERROR, data,data2,data3, message)
        }

        fun <T,T2,T3> loading(data: T?,data2: T2?,data3:T3?): APIResult<T,T2,T3> {
            return APIResult(Status.LOADING, data,data2,data3, null)
        }


    }
}