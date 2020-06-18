package com.example.skylinepropertymanagement.ui.activity.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.skylinepropertymanagement.R
import com.example.skylinepropertymanagement.app.toast
import com.example.skylinepropertymanagement.databinding.ActivityRegisterBinding
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.switch_user
import kotlinx.android.synthetic.main.activity_register.text_layout_name
import kotlinx.android.synthetic.main.activity_register.text_layout_password

class RegisterActivity : AppCompatActivity() {
    lateinit var mBinding:ActivityRegisterBinding
    lateinit var viewModel:AuthViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)
        mBinding.authViewModel = viewModel
        mBinding.lifecycleOwner = this

        init()
    }

    private fun init() {

        viewModel.typeSwitch.value = true

        viewModel.loginResult.observe(this, Observer<String> {
            text_layout_name.isErrorEnabled = false
            text_layout_email.isErrorEnabled = false
            text_layout_mobile.isErrorEnabled = false
            text_layout_password.isErrorEnabled = false
            when(it) {
                "name" -> {text_layout_name.error = "Name is required"}
                "email" -> {text_layout_email.error = "Email is required"}
                "mobile" -> {text_layout_mobile.error = "Mobile is required"}
                "password" -> {text_layout_password.error = "Password is required"}
                "success" -> {
                    text_layout_name.isErrorEnabled = false
                    text_layout_email.isErrorEnabled = false
                    text_layout_mobile.isErrorEnabled = false
                    text_layout_password.isErrorEnabled = false
                    startActivity(Intent(this,LoginActivity::class.java))
                }
            }
        })

        viewModel.typeSwitch.observe(this, Observer<Boolean> { t ->
            when(t) {
                true -> switch_user.text = "Landlord"
                false -> switch_user.text = "Tennant"
            }
        })


    }
}