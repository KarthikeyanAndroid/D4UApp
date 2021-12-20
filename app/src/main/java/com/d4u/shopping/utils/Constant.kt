package com.d4u.shopping.utils

import android.annotation.SuppressLint


object Constant {
    lateinit var CAT_ID: String
    lateinit var BUSINESS_ID: String
    const val STATUS_CODE: Int = 1
    const val WRON_MSG: String = "Something went wrong"
    const val SESSION_NAME: String = "D4U_Session"
    const val USER_ID: String = "user_id"
    const val ACCESS_TOKEN: String = "user_access_token"
    const val USER_EMAIL: String = "user_email"
    const val USER_NAME: String = "user_name"
    const val BACK_STACK_ROOT_TAG = "root_fragment"
    const val BACK_STACK_ZERO = "0"
    const val BUSINESS_LOGO_PATH = "https://featspacesolution.com/projects/dealsunlock/business_logo/"
    const val PRODUCT_LIST_LOGO_PATH = "https://featspacesolution.com/projects/dealsunlock/product_image/"
    const val CUSTOMER_BASED_PRODUCT = "https://featspacesolution.com/projects/dealsunlock/offer_image/"
    const val CUSTOMER_LOGO_PATH = "https://featspacesolution.com/projects/dealsunlock/business_logo/"

    @SuppressLint("AuthLeak")
    const val BASEURL: String = "https://featspacesolution.com/projects/dealsunlock/admin/api/"
}