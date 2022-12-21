package com.androbim.reddyapi.git

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GitApiInterface {

    //repositories?q=hyderabad
    @GET("repositories")
    fun getDataFromGitApi(@Query(value = "q") cityName:String):Call<GitModel>
}