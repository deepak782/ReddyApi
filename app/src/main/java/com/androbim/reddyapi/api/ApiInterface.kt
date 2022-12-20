package com.androbim.reddyapi.api

import com.androbim.reddyapi.multipleObject.ListModel
import com.androbim.reddyapi.multipleObject.ListModelItem
import com.androbim.reddyapi.object1.ListModel1
import com.androbim.reddyapi.singleObject.SingleModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET

interface ApiInterface {

    @GET("7260d8b8-828d-4558-bfb1-6a0516c61ddd")
    fun getData1(): Call<SingleModel>

    @GET("417e28c8-988d-462f-830e-efd063a60bf4")
    fun getDate2():Call<List<ListModelItem>>

    @GET("482a6837-1770-4f8c-ab33-b2a36b7088fc")
    fun getData3():Call<ListModel1>
}