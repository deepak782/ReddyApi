package com.androbim.reddyapi.gorest.goretRetrofitInstance

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GorestInstance {

    //https://gorest.co.in/public/v2/users
    val BASE_URL="https://gorest.co.in/public/v2/"

    fun getInstance():Retrofit{


        //okHttp Interceptors and add dependency
        val loggingInterceptor=HttpLoggingInterceptor()
        loggingInterceptor.level=(HttpLoggingInterceptor.Level.BODY)
        val client= OkHttpClient.Builder()
        client.addInterceptor(loggingInterceptor)

        val retrofit=Retrofit.Builder().baseUrl(BASE_URL)
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create()).build()
        return retrofit

    }
}