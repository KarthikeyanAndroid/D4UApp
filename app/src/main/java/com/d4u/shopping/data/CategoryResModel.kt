package com.d4u.shopping.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


/**
 * Created by Karthikeyan V on 15,October,2021
 */
data class CategoryResModel(@SerializedName("category")
                            @Expose var cateogryList:ArrayList<OfferCategory>,var status:Boolean)
