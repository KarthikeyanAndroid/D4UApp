package com.d4u.shopping.data

import com.google.gson.annotations.SerializedName


/**
 * Created by Karthikeyan V on 13,October,2021
 */
data class CustomerResponseModel(@SerializedName("customer") val customer: ArrayList<Customer>,
                                 @SerializedName("status") val status: Boolean)
