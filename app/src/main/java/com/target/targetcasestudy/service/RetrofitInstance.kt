package com.target.targetcasestudy.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object {
        private var retrofit: Retrofit? = null
        private const val BASE_URL: String = "https://api.target.com/mobile_case_study_deals/v1/"

        fun getService(): RetrofitService? {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!.create<RetrofitService>(RetrofitService::class.java)
        }

    }
}