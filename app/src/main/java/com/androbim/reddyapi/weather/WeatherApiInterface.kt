package com.androbim.reddyapi.weather

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface WeatherApiInterface {

    //weather?q=Hyderabad&appid=a847de79ed9e5152022f478c2675fdf2
    @POST("weather")
    fun getWeatherReport(@Query(value = "q") cityname:String,@Query(value="appid") apiKey:String): Call<WeatherModel>
}