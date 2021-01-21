package com.target.targetcasestudy.service

import com.target.targetcasestudy.model.AllDealsResponse
import com.target.targetcasestudy.model.SingleItemResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {

    @GET("deals")
    fun getAllDeals(): Call<AllDealsResponse>

    @GET("deals/{id}")
    fun getItemData(@Path(value = "id", encoded = true) id: Int): Call<SingleItemResponse>

}