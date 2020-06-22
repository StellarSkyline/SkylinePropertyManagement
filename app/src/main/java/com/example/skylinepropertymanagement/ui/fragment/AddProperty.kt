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
import com.example.skylinepropertymanagement.databinding.FragmentAddPropertyBinding

class AddProperty: Fragment() {
    lateinit var mBinding: FragmentAddPropertyBinding
    val viewModel: FragmentViewModel by activityViewModels()
    val navController by lazy {findNavController()}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_property, container, false)
        mBinding.fragmentViewModel = viewModel
        mBinding.lifecycleOwner = this

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.checkJump.observe(viewLifecycleOwner, Observer {
            if(it ==false) {
                viewModel.repo.propertyData.value = viewModel.repo.propertyData.value
                navController.navigate(R.id.action_addProperty_to_propertiesFragment)

            }
        })

    }
}