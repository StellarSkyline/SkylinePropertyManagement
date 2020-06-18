package com.example.skylinepropertymanagement.app

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.app_bar.*

fun Context.log(message:String) {
    Log.d("STLog", message)
}

fun Context.toast(message:String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.setupToolbar(title:String) {
    var myToolbar: Toolbar = this.toolbar
    myToolbar.title = title
    this.setSupportActionBar(myToolbar)
}