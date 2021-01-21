package com.target.targetcasestudy.model

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.target.targetcasestudy.service.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItemsRepository(private val application: Application? = null) {

    private val allDealsMutableLiveData: MutableLiveData<List<AllDealsData>> = MutableLiveData()

    private val singleItemMutableLiveData: MutableLiveData<SingleItemData> = MutableLiveData()

    fun getAllDeals(): MutableLiveData<List<AllDealsData>> {

        val allDealsCall: Call<AllDealsResponse> =
            RetrofitInstance.getService()!!.getAllDeals()

        allDealsCall.enqueue(object : Callback<AllDealsResponse> {
            override fun onFailure(call: Call<AllDealsResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.localizedMessage}")
            }

            override fun onResponse(
                call: Call<AllDealsResponse>,
                response: Response<AllDealsResponse>
            ) {
                if (response.body() != null && response.body()!!.products != null && response.body()!!.products!!.isNotEmpty()) {
                    val allDealsDataList = arrayListOf<AllDealsData>()
                    response.body()!!.products?.forEach {
                        allDealsDataList.add(
                            AllDealsData(
                                it?.id,
                                it?.title,
                                it?.aisle?.capitalize(),
                                it?.imageUrl,
                                it?.regularPrice?.displayString
                            )
                        )
                    }
                    allDealsMutableLiveData.value = allDealsDataList
                } else {
                    Log.e(TAG, "onResponse: $response")
                }
            }

        })
        return allDealsMutableLiveData

    }

    fun getSingleItemDetails(itemId: Int): MutableLiveData<SingleItemData> {

        val singleItemCall: Call<SingleItemResponse> =
            RetrofitInstance.getService()!!.getItemData(itemId)

        singleItemCall.enqueue(object : Callback<SingleItemResponse> {
            override fun onFailure(call: Call<SingleItemResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.localizedMessage}")
            }

            override fun onResponse(
                call: Call<SingleItemResponse>,
                response: Response<SingleItemResponse>
            ) {
                if (response.body() != null) {
                    singleItemMutableLiveData.value = SingleItemData(
                        response.body()!!.imageUrl,
                        response.body()!!.regularPriceForSingleItem?.displayString,
                        response.body()!!.title,
                        response.body()!!.description
                    )
                } else {
                    Log.e(TAG, "onResponse: $response")
                }
            }

        })

        return singleItemMutableLiveData
    }

    companion object {
        private const val TAG = "AllDealsRepository"
    }


}