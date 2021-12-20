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
class CategoryViewModel @Inject constructor(private var categoryRepository: CategoryRepository, private var customerRepository: CustomerRepository) :
        ViewModel() {

    val TAG: String = javaClass.simpleName
    var categoryLiveData: MutableLiveData<APIResult<CategoryResModel, GetHomeProductModel, CustomerResponseModel>> = MutableLiveData()
    var categoryLiveData1: MutableLiveData<APIResult<CategoryResModel, GetCatWiseHomeProductModel, CustomerResponseModel>> = MutableLiveData()
    var categoryLiveData3: MutableLiveData<APIResult<CategoryResModel, GetCatWiseHomeProductModel, CustomerResponseModel>> = MutableLiveData()
    var categoryLiveData2: MutableLiveData<APIResult<CategoryResModel, GetCatWiseHomeProductModel, CustomerResponseModel>> = MutableLiveData()
    var offerDetailLiveData: MutableLiveData<APIResult<OfferDetailsResponse, GetCatWiseHomeProductModel, CustomerResponseModel>> = MutableLiveData()
//    var homeProductLiveData: MutableLiveData<APIResult<GetHomeProductModel>> = MutableLiveData()


    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        categoryLiveData.postValue(APIResult.error(Constant.WRON_MSG, null, null, null))
    }
    private val catWiseOffer = CoroutineExceptionHandler { _, exception ->
        Log.i(TAG, ": " + exception.message)
        categoryLiveData1.postValue(APIResult.error(Constant.WRON_MSG, null, null, null))
    }
    private val businessWiseHandler = CoroutineExceptionHandler { _, exception ->
        Log.i(TAG, ": " + exception.message)
        categoryLiveData2.postValue(APIResult.error(Constant.WRON_MSG, null, null, null))
    }
    private val offerDetailWiseHandler = CoroutineExceptionHandler { _, exception ->
        Log.i(TAG, ": " + exception.message)
        offerDetailLiveData.postValue(APIResult.error(Constant.WRON_MSG, null, null, null))
    }


    var categoryResModel: CategoryResModel? = null
    var getHomeProductModel: GetHomeProductModel? = null
    var getCustomerModel: CustomerResponseModel? = null
    fun getHomeApiCall() {
        viewModelScope.launch {
            (exceptionHandler)
            supervisorScope {

                categoryLiveData.postValue(APIResult.loading(null, null, null))
                val homeProductCall = async { categoryRepository.getHomeProduct() }
                val customerCall = async { customerRepository.getCustomer() }
                val cateogryCall = async { categoryRepository.getCategory() }
                categoryResModel = try {
                    cateogryCall.await()
                } catch (ex: Exception) {
                    categoryLiveData.postValue(APIResult.error(Constant.WRON_MSG, null, null, null))

                    null
                }

                getHomeProductModel = try {
                    homeProductCall.await()
                } catch (ex: Exception) {
                    categoryLiveData.postValue(APIResult.error(Constant.WRON_MSG, null, null, null))
                    null
                }
                getCustomerModel = try {
                    customerCall.await()

                } catch (ex: Exception) {
                    Log.i(TAG, "getHomeApiCall: " + ex.message)
                    categoryLiveData.postValue(APIResult.error(Constant.WRON_MSG, null, null, null))
                    null
                }

                categoryLiveData.postValue(APIResult.success(categoryResModel, getHomeProductModel, getCustomerModel))
//                homeProductLiveData.postValue(APIResult.success(getHomeProductModel))

//                processHomeApiCall(getHomeProductModel, categoryResModel)
            }
        }
    }

    fun getCatWiseProductApi(cat_id: String) {
        viewModelScope.launch {
            (catWiseOffer)
            categoryLiveData1.postValue(APIResult.loading(null, null, null))
            val cateWiseData = categoryRepository.getCatWiseProduct(cat_id)
            Log.i(TAG, "getCatWiseProduct: " + Gson().toJson(cateWiseData))
            categoryLiveData1.postValue(APIResult.success(null, cateWiseData, null))

        }
    }

        fun getCustomerWiseProductId(businessId: String) {
            viewModelScope.launch {
                (catWiseOffer)
                categoryLiveData3.postValue(APIResult.loading(null, null, null))
                val cateWiseData = categoryRepository.getCustomerWiseOffer(businessId)
                Log.i(TAG, "getCatWiseProduct: " + Gson().toJson(cateWiseData))
                categoryLiveData3.postValue(APIResult.success(null, cateWiseData, null))

            }

        }

        fun getBusinessWise(cat_id: String, businessId: String) {
            viewModelScope.launch {
                (businessWiseHandler)
                categoryLiveData2.postValue(APIResult.loading(null, null, null))
                val businessWise = categoryRepository.getBusinessWiseProduct(cat_id, businessId)
                Log.i(TAG, "getCatWiseProduct: " + Gson().toJson(businessWise))
                categoryLiveData2.postValue(APIResult.success(null, businessWise, null))

            }
        }
        fun getOfferDetails(offerId: String) {
            viewModelScope.launch {
                (offerDetailWiseHandler)
                offerDetailLiveData.postValue(APIResult.loading(null, null, null))
                val offerDetail = categoryRepository.getOfferDetail(offerId)
                Log.i(TAG, "getCatWiseProduct: " + Gson().toJson(offerDetail))
                offerDetailLiveData.postValue(APIResult.success(offerDetail, null, null))

            }
        }
    }









