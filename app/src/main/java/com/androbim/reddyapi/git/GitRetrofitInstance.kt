package com.androbim.reddyapi.git

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GitRetrofitInstance {
    //https://api.github.com/search/repositories?q=hyderabad

    val BASE_URL="https://api.github.com/search/"

    fun getInstance():Retrofit{
        val retrofit=Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        return retrofit
    }
}