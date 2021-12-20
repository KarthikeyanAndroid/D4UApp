package com.d4u.shopping.data


/**
 * Created by Karthikeyan V on 18,October,2021
 */


/* "id": 3,
      "business_id": "3",
      "category": "4",
      "sub_category": "12",
      "product_image": "32eacabbe3c2d0e688e33c25c3e0f486.jpg",
      "actual_amount": "4500",
      "offer_amount": "3700",
      "created_at": "2021-08-20 19:03:12",
      "updated_at": null,
      "is_active": "0",
      "customer_id": "3",
      "business_name": "Rk Soft Tech Solution",
      "business_logo": "b65600e912c7bdc9006061f0751ece42.png"*/
data class BusinessList(var customer_id: String, var business_name: String,

                        var business_logo: String)
