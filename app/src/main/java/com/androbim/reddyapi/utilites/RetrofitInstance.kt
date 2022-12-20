package com.androbim.reddyapi.utilites

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// https://run.mocky.io/v3/7260d8b8-828d-4558-bfb1-6a0516c61ddd

object RetrofitInstance {

    val BASE_URL="https://run.mocky.io/v3/";

    fun getInstance():Retrofit{
       val retrofit=Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
       return retrofit
    }
}