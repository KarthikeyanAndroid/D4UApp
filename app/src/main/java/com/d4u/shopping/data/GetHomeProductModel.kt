package com.d4u.shopping.data


/**
 * Created by Karthikeyan V on 18,October,2021
 */
data class GetHomeProductModel(var status: Boolean,

                               var offers: ArrayList<HomeProductList>,var business: ArrayList<BusinessList>)
