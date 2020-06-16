package com.example.skylinepropertymanagement.ui.activity.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.skylinepropertymanagement.R
import com.example.skylinepropertymanagement.app.toast
import com.example.skylinepropertymanagement.databinding.ActivityLoginBinding
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.view.*

class LoginActivity : AppCompatActivity() {

    lateinit var mBinding: ActivityLoginBinding
    lateinit var viewModel:AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)

        mBinding.authViewModel = viewModel
        mBinding.lifecycleOwner = this

        init()

    }

    private fun init() {
        //Set initial value for Typeswitch
        viewModel.typeSwitch.value = true

        viewModel.loginResult.observe(this, object:Observer<String>{
            override fun onChanged(t: String?) {
                text_layout_email.isErrorEnabled = false
                text_layout_password.isErrorEnabled = false
                when(t) {
                    "email" -> {text_layout_email.error = "Email is required"}
                    "password" ->{text_layout_password.error = "Password is required"}
                    "success" -> {
                        text_layout_email.isErrorEnabled = false
                        text_layout_password.isErrorEnabled = false
                        applicationContext.toast("Login Success, Check Logcat")}
                }
            }
        })

        viewModel.typeSwitch.observe(this, object:Observer<Boolean>{
            override fun onChanged(t: Boolean?) {
                when(t) {
                    true -> switch_user.text = "Landlord"
                    false -> switch_user.text = "Tennant"
                }
            }

        })

        text_view_register.setOnClickListener {
            startActivity(Intent(this,RegisterActivity::class.java))
        }

    }


}