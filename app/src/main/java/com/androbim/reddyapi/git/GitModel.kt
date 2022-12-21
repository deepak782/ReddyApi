package com.androbim.reddyapi.git


data class GitModel(val items:List<GitList>)
data class GitList(val name:String,val full_name:String,val description:String,val owner: Owner1)
data class Owner1(val login:String,val id:Int)

