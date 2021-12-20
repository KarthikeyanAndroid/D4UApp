package com.d4u.shopping.data

import com.google.gson.annotations.SerializedName


/**
 * Created by Karthikeyan V on 27,November,2021
 */

data class OfferDetailsResponse(
        val offers: Welcome5Offers,
        val status: Boolean
)

data class Welcome5Offers(
        val offers: OtherOfferClass,

        @SerializedName("offers_catelog")
        val offersCatelog: ArrayList<OffersCatelog>,

        @SerializedName("other_offers")
        val otherOffers: ArrayList<OtherOfferClass>
)

data class OtherOfferClass(
        val image: String? = null,

        @SerializedName("offer_name")
        val offerName: String,

        @SerializedName("valid_to_date")
        val validToDate: String,

        @SerializedName("business_id")
        val businessID: String,

        @SerializedName("business_url")
        val businessURL: String? = null
)

data class OffersCatelog(
        val image: String
)
