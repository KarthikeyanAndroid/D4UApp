package com.d4u.shopping.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.oviesmarterware.ovie.utils.APIResult
import com.d4u.shopping.data.CustomerResponseModel
import com.d4u.shopping.repository.CustomerRepository
import com.d4u.shopping.utils.Constant

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Karthikeyan on 31/03/2021.
 */
class CustomerViewModel @Inject constructor(private var customerRepository: CustomerRepository) :
    ViewModel() {

  /*  val TAG: String = javaClass.simpleName
    var customerLiveData: MutableLiveData<APIResult<CustomerResponseModel>> = MutableLiveData()


    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        customerLiveData.postValue(APIResult.error(Constant.WRON_MSG, null))
    }*/
  /*  private val viewExceptionHandler = CoroutineExceptionHandler { _, exception ->
        customerViewLiveData.postValue(APIResult.error(Constant.WRON_MSG, null))
    }
    private val brandExceptionHandler = CoroutineExceptionHandler { _, exception ->
        brandLiveData.postValue(APIResult.error(Constant.WRON_MSG, null))
    }
    private val updateExceptionHandler = CoroutineExceptionHandler { _, exception ->
        updateOrderLiveData.postValue(APIResult.error(Constant.WRON_MSG, null))
    }*/

   /* fun customerData() {
        viewModelScope.launch(exceptionHandler) {
            customerLiveData.postValue(APIResult.loading(null))
            val getCustomerData = customerRepository.getCustomer()
            Log.i(TAG, "postLoginData: " + Gson().toJson(getCustomerData))
            customerLiveData.postValue(APIResult.success(getCustomerData))
        }
    }*/


}