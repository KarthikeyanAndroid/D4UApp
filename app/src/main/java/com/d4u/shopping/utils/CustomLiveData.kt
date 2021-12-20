package com.d4u.shopping.utils

import androidx.lifecycle.MutableLiveData
import retrofit2.Response

/**
 * Created by Karthikeyan on 27-01-2021.
 */
class CustomLiveData<T> : MutableLiveData<APIResponse<T>>() {
    fun postLoading() {
        postValue(APIResponse<T>().loading())
    }

    fun postFailure(response: Response<T>) {
        postValue(APIResponse<T>().failure(response))
    }

    fun postError(throwable: Throwable) {
        postValue(APIResponse<T>().error(throwable))
    }

    fun postSuccess(data: T) {
        postValue(APIResponse<T>().success(data))
    }

    fun setSuccess(data: T) {
        value = APIResponse<T>().success(data)
    }

    fun setFailure(response: Response<T>) {
        value = APIResponse<T>().failure(response)
    }

    fun setError(throwable: Throwable) {
        value = APIResponse<T>().error(throwable)
    }

    fun postListSuccess(data: List<T>) {
        postValue(APIResponse<T>().success(data))
    }


}