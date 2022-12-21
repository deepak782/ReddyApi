package com.androbim.reddyapi.weather

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherRetrofitInstance {
    //https://api.openweathermap.org/data/2.5/weather?q=Hyderabad&appid=a847de79ed9e5152022f478c2675fdf2
    val BASE_URL="https://api.openweathermap.org/data/2.5/"

    fun getInstance():Retrofit{
        val retrofit=Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        return retrofit
    }
}