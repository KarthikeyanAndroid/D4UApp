package com.d4u.shopping.repository

import android.util.Log
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.oviesmarterware.ovie.network.ApiRequest

import com.d4u.shopping.app.webservice.APIServices
import com.d4u.shopping.data.*
import javax.inject.Inject

/**
 * Created by Karthikeyan on 31/03/2021.
 */
class CategoryRepository @Inject constructor(private var apiServices: APIServices) : ApiRequest() {
    private val TAG: String = javaClass.simpleName
    suspend fun getCategory(): CategoryResModel {
        val response = apiRequest { apiServices.getCategory().await() }
        return response
    }

    suspend fun getHomeProduct(): GetHomeProductModel {
        val viewResponseDTO = apiRequest { apiServices.getHomeProducts().await() }
        return viewResponseDTO
    }

    suspend fun getCatWiseProduct(catId: String): GetCatWiseHomeProductModel {
        val viewResponseModel = apiRequest { apiServices.getCatWiseOffer(catId).await() }
        Log.i(TAG, "getCatWiseProduct: " + Gson().toJson(viewResponseModel))
        return viewResponseModel
    }

    suspend fun getCustomerWiseOffer(businessId: String): GetCatWiseHomeProductModel {
        val viewResponseModel = apiRequest { apiServices.getCustomerWiseOffer(businessId).await() }
        Log.i(TAG, "getCustomerWiseOffer: " + Gson().toJson(viewResponseModel))
        return viewResponseModel
    }

    suspend fun getBusinessWiseProduct(catId: String, businessId: String): GetCatWiseHomeProductModel {
        val viewResponseModel = apiRequest { apiServices.getCatBusinessWise_offer(catId, businessId).await() }
        Log.i(TAG, "getBusinessWiseProduct: " + Gson().toJson(viewResponseModel))
        return viewResponseModel
    }

    suspend fun getOfferDetail(offerId: String): OfferDetailsResponse {
        val offerDetailResponseModel = apiRequest { apiServices.getOfferDetails(offerId).await() }
        return offerDetailResponseModel
    }
/*
    suspend fun getViewCustomer(id: String): ViewCustomerDTO {
        val viewResponseDTO = apiRequest { apiServices.viewCustomer(id).await() }
        return viewResponseDTO
    }

    suspend fun getBrand(): BrandResponseDTO {
        val brandData = apiRequest { apiServices.getBrandRequest().await() }
        return brandData
    }

    suspend fun updateOrder(updateCustomerDTO: UpdateCustomerDTO): CommonResponseDTO {
        val brandData = apiRequest { apiServices.updateOrder(updateCustomerDTO).await() }
        return brandData
    }*/

}