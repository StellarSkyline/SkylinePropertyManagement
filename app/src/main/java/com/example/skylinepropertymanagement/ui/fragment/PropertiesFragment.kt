package com.example.skylinepropertymanagement.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.skylinepropertymanagement.R
import com.example.skylinepropertymanagement.app.Jump
import com.example.skylinepropertymanagement.app.onlyNew
import com.example.skylinepropertymanagement.databinding.FragmentPropertiesBinding
import com.example.skylinepropertymanagement.databinding.FragmentVendorBinding

class PropertiesFragment: Fragment() {
    lateinit var mBinding: FragmentPropertiesBinding
    val viewModel: FragmentViewModel by activityViewModels()

    val navController by lazy {findNavController()}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //viewModel = ViewModelProviders.of(requireActivity()).get(FragmentViewModel::class.java)
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_properties, container, false)
        mBinding.fragmentViewModel = viewModel
        mBinding.lifecycleOwner = this

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.checkJump.observe(viewLifecycleOwner, Observer {
            navController.navigate(R.id.action_propertiesFragment_to_addProperty)
        })


    }
}