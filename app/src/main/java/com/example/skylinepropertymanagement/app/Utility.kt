package com.example.skylinepropertymanagement.app

import android.content.Context
import android.util.Log
import android.widget.Toast

fun Context.log(message:String) {
    Log.d("STLog", message)
}

fun Context.toast(message:String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}