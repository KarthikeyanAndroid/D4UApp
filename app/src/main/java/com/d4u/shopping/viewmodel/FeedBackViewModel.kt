package com.d4u.shopping.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.oviesmarterware.ovie.utils.APIResult
import com.d4u.shopping.data.*
import com.d4u.shopping.repository.CategoryRepository
import com.d4u.shopping.repository.CustomerRepository
import com.d4u.shopping.repository.FeedbackRepository
import com.d4u.shopping.utils.Constant

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope
import java.lang.Exception
import javax.inject.Inject

/**
 * Created by Karthikeyan on 31/03/2021.
 */
class FeedBackViewModel @Inject constructor(private var feedbackRepository: FeedbackRepository, private var customerRepository: CustomerRepository) :
        ViewModel() {

    val TAG: String = javaClass.simpleName
    var feedbackLiveData: MutableLiveData<APIResult<CommonResponseModel, GetHomeProductModel, CustomerResponseModel>> = MutableLiveData()
//    var homeProductLiveData: MutableLiveData<APIResult<GetHomeProductModel>> = MutableLiveData()


    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        feedbackLiveData.postValue(APIResult.error(Constant.WRON_MSG, null, null, null))
    }



    fun getFeedBackApi(feedBackParamModel: FeedBackParamModel) {
        viewModelScope.launch {
            (exceptionHandler)
            feedbackLiveData.postValue(APIResult.loading(null, null, null))
            val feedBackData = feedbackRepository.feedBackPost(feedBackParamModel)
            Log.i(TAG, "getCatWiseProduct: "+Gson().toJson(feedBackData))
            feedbackLiveData.postValue(APIResult.success(feedBackData, null, null))

        }

    }
}







