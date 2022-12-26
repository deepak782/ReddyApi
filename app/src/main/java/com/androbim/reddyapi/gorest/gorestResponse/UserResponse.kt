package com.androbim.reddyapi.gorest.gorestResponse

data class UserResponse(val id:Int,val params: UserParameters)
data class UserParameters(val name:String,val email:String,val gender:String,val status:String)
