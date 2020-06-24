package com.example.skylinepropertymanagement.data.model

import java.io.Serializable

data class Property(
    val id: String,
    val propertyaddress: String,
    val propertycity: String,
    val propertycountry: String,
    val propertymortageinfo: String,
    val propertypurchaseprice: String,
    val propertystate: String,
    val propertystatus: String
):Serializable