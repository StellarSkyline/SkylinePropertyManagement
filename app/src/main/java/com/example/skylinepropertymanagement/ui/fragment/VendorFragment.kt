package com.example.skylinepropertymanagement.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.skylinepropertymanagement.R
import com.example.skylinepropertymanagement.databinding.FragmentVendorBinding

class VendorFragment: Fragment() {
    lateinit var mBinding: FragmentVendorBinding
    val viewModel: FragmentViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //intilze data binding - this is replaced with requireActivity() if in a fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_vendor, container, false)
        mBinding.fragmentViewModel = viewModel
        mBinding.lifecycleOwner = this

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}