package com.example.skylinepropertymanagement.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.skylinepropertymanagement.R
import com.example.skylinepropertymanagement.app.onlyNew
import com.example.skylinepropertymanagement.data.model.Property
import com.example.skylinepropertymanagement.databinding.FragmentAddTenantBinding
import kotlinx.android.synthetic.main.fragment_add_tenant.*

class FragmentAddTenant: Fragment() {
    lateinit var mBinding: FragmentAddTenantBinding
    val viewModel: FragmentViewModel by activityViewModels()
    val navController by lazy {findNavController()}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_tenant, container, false)
        mBinding.fragmentViewModel = viewModel
        mBinding.lifecycleOwner = this

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var property = arguments?.getSerializable("id") as Property

        viewModel.inputVal.onlyNew(this).observe(viewLifecycleOwner, Observer{
            when(it) {
                "name" -> {text_input_name_tennant.error = "Name is Required"}
                "email" -> {text_input_email_tennant.error = "Email is required"}
                "mobile" -> {text_input_mobile_tennant.error = "Mobile is required"}
                "success" -> {
                    text_input_name_tennant.isErrorEnabled = false
                    text_input_email_tennant.isErrorEnabled = false
                    text_input_mobile_tennant.isErrorEnabled = false
                    viewModel.propertyid.value = property.id
                    viewModel.address.value = property.propertyaddress
                }
            }

        })


    }
}