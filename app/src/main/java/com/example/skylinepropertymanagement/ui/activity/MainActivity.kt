package com.example.skylinepropertymanagement.ui.activity

import android.content.Intent
import android.content.Intent.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.skylinepropertymanagement.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        var intent = Intent(this, LoginActivity::class.java)

        var handler = Handler()

        handler.postDelayed({
            intent.flags = FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }, 3000)

    }
}