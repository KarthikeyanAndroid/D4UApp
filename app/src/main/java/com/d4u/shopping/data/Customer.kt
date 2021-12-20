package com.d4u.shopping.data

import com.google.gson.annotations.SerializedName


/**
 * Created by Karthikeyan V on 13,October,2021
 */
data class Customer(@SerializedName("id") val id: Int,
                    @SerializedName("contact_person") val contact_person: String,
                    @SerializedName("contact_no") val contact_no: String,
                    @SerializedName("business_name") val business_name: String,
                    @SerializedName("business_logo") val business_logo: String,
                    @SerializedName("business_address") val business_address: String,
                    @SerializedName("mail") val mail: String,
                    @SerializedName("branch_name") val branch_name: String,
                    @SerializedName("created_at") val created_at: String,
                    @SerializedName("updated_at") val updated_at: String,
                    @SerializedName("is_active") val is_active: Int)
