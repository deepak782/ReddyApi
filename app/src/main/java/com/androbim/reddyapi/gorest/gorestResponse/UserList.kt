package com.androbim.reddyapi.gorest.gorestResponse

data class UserList(val listUser:List<GorestList>)
data class GorestList(val id:Int,val name:String,val email:String,val gender:String,val status:String)
