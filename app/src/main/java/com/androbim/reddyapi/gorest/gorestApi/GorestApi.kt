package com.androbim.reddyapi.gorest.gorestApi

import com.androbim.reddyapi.gorest.gorestResponse.GorestList
import com.androbim.reddyapi.gorest.gorestResponse.UserParameters
import com.androbim.reddyapi.gorest.gorestResponse.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface GorestApi {

    @GET("users")
    @Headers("Accept:application/json","Content-Type:application/json",
        "Authorization: Bearer 8b99b5a6ce3cd26e00822d811f1c2ef4a9023c0ace89d38ba9dfa8138018509a")
    fun goRestList():Call<List<GorestList>>

    @POST("users")
    @Headers("Accept:application/json","Content-Type:application/json",
        "Authorization: Bearer 8b99b5a6ce3cd26e00822d811f1c2ef4a9023c0ace89d38ba9dfa8138018509a")
    fun goRestCreate(@Body params:UserParameters):Call<UserResponse>

    @PATCH("users/{user_id}")
    @Headers("Accept:application/json","Content-Type:application/json",
        "Authorization: Bearer 8b99b5a6ce3cd26e00822d811f1c2ef4a9023c0ace89d38ba9dfa8138018509a")
    fun goRestUpdate(@Path("user_id") user_id:String,@Body params:UserParameters):Call<UserResponse>

    @DELETE("users/{user_id}")
    @Headers("Accept:application/json","Content-Type:application/json",
        "Authorization: Bearer 8b99b5a6ce3cd26e00822d811f1c2ef4a9023c0ace89d38ba9dfa8138018509a")
    fun goRestDelete(@Path("user_id") user_id:String):Call<UserResponse>
}