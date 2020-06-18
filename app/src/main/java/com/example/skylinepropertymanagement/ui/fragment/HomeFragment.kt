package com.example.skylinepropertymanagement.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProviders
import com.example.skylinepropertymanagement.R
import com.example.skylinepropertymanagement.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*
import okhttp3.Response

class HomeFragment: Fragment() {

    //initalize global variables
    lateinit var mBinding:FragmentHomeBinding
    lateinit var viewModel:FragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //intilze data binding - this is replaced with requireActivity() if in a fragment
        mBinding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_home)
        viewModel = ViewModelProviders.of(requireActivity()).get(FragmentViewModel::class.java)
        mBinding.fragmentViewModel = viewModel
        mBinding.lifecycleOwner = this

        return inflater.inflate(R.layout.fragment_home,container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadProperties()




    }



}