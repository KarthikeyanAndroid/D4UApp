package com.d4u.shopping.app.webservice

import com.d4u.shopping.data.*
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*


/**
 * Created by Karthikeyan on 24-02-2021.
 */
interface APIServices {

    @GET("get_customer")
    fun customerRequest(): Deferred<Response<CustomerResponseModel>>

    @GET("get_category")
    fun getCategory(): Deferred<Response<CategoryResModel>>

    @GET("home_offer")
    fun getHomeProducts(): Deferred<Response<GetHomeProductModel>>

    @FormUrlEncoded
    @POST("categorywise_offer")
    fun getCatWiseOffer(@Field("cat_id") catId: String): Deferred<Response<GetCatWiseHomeProductModel>>

    @FormUrlEncoded
    @POST("customerwise_offer")
    fun getCustomerWiseOffer(@Field("business_id") businessId: String): Deferred<Response<GetCatWiseHomeProductModel>>

    @POST("users_feedback")
    fun postFeedback(@Body feedBackParamModel: FeedBackParamModel): Deferred<Response<CommonResponseModel>>

    @FormUrlEncoded
    @POST("cat_business_wise_offer")
    fun getCatBusinessWise_offer(@Field("cat_id") catId: String, @Field("business_id") businessId: String): Deferred<Response<GetCatWiseHomeProductModel>>

    @FormUrlEncoded
    @POST("offer_details")
    fun getOfferDetails(@Field("offer_id") offerId:String):Deferred<Response<OfferDetailsResponse>>
/*
    //login
    @FormUrlEncoded
    @POST("login")
    fun loginRequest(
        @Field("username") userName: String,
        @Field("password") password: String
    ): Deferred<Response<LoginResponseDTO>>


    @GET("customers")
    fun customerRequest(): Deferred<Response<CustomerListRespDTO>>

    @GET("brand")
    fun getBrandRequest(): Deferred<Response<BrandResponseDTO>>


    @POST("update_order")
    fun updateOrder(@Body updateCustomerDTO: UpdateCustomerDTO): Deferred<Response<CommonResponseDTO>>

    @FormUrlEncoded
    @POST("expenses")
    fun getExpensesList(@Field("staff_id") staff_id: String): Deferred<Response<StaffResponseDTO>>

    @FormUrlEncoded
    @POST("view_order")
    fun viewCustomer(@Field("id") id: String): Deferred<Response<ViewCustomerDTO>>


    @FormUrlEncoded
    @POST("add_expenses")
    fun addExpenses(
        @Field("staff_id") staff_id: String,
        @Field("remarks") remarks: String,
        @Field("amount") amount: String, @Field("entry_date") entryDate: String
    ): Deferred<Response<CommonResponseDTO>>

    @FormUrlEncoded
    @POST("add_staff_allowance")
    fun addVechAllow(
        @Field("staff_id") staff_id: String,
        @Field("start_km") startKm: String,
        @Field("end_km") endKm: String, @Field("entry_date") entryDate: String
    ): Deferred<Response<CommonResponseDTO>>


    @FormUrlEncoded
    @POST("staff_allowance")
    fun vechicleAllowanceList(@Field("staff_id") staff_id: String): Deferred<Response<VechicelAllowance>>

    @FormUrlEncoded
    @POST("home_screen")
    fun homeScreen(@Field("staff_id") staff_id: String): Deferred<Response<HomeResponseModelDTO>>

    @FormUrlEncoded
    @POST("forget_password")
    fun forgotPassword(@Field("mobile_no") mobile_no: String):Deferred<Response<CommonResponseDTO>>

    @FormUrlEncoded
    @POST("reset_code_check")
    fun resetCode(@Field("mobile_no") mobile_no: String, @Field("code") code: String):Deferred<Response<CommonResponseDTO>>

    @FormUrlEncoded
    @POST("set_password")
    fun changePassword(@Field("staff_id") staff_id: String, @Field("password") password: String):Deferred<Response<CommonResponseDTO>>
*/

}