package com.example.skylinepropertymanagement.data.model

data class LoginResponse(
    val msg: Any,
    val userid: String,
    val usertype: String,
    val useremail: String,
    val appapikey: String
)