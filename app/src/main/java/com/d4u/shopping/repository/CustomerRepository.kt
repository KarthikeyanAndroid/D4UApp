package com.d4u.shopping.repository

import com.google.android.material.tabs.TabLayout
import com.oviesmarterware.ovie.network.ApiRequest
import com.d4u.shopping.data.CustomerResponseModel

import com.d4u.shopping.app.webservice.APIServices
import javax.inject.Inject

/**
 * Created by Karthikeyan on 31/03/2021.
 */
class CustomerRepository @Inject constructor(private var apiServices: APIServices) : ApiRequest() {
    private val TAG: String = javaClass.simpleName
    suspend fun getCustomer(): CustomerResponseModel {
        val response = apiRequest { apiServices.customerRequest().await() }
        return response
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