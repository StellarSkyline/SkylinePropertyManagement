package com.example.skylinepropertymanagement.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.skylinepropertymanagement.R
import com.example.skylinepropertymanagement.app.App
import com.example.skylinepropertymanagement.app.log
import com.example.skylinepropertymanagement.data.adapter.AdapterProperties
import com.example.skylinepropertymanagement.data.model.Property
//import com.example.skylinepropertymanagement.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment: Fragment() {
    val viewModel: FragmentViewModel by viewModels()
    var mList:List<Property>? = emptyList()
    lateinit var adapter: AdapterProperties

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_home,container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = AdapterProperties(activity?.applicationContext!!)

        recycler_view.layoutManager = LinearLayoutManager(activity?.applicationContext)
        recycler_view.adapter = adapter

        viewModel.repo.getProperties()

        viewModel.repo.propertyData.observe(viewLifecycleOwner, Observer{
            mList = it!!
                App.instance.log(mList.toString())
                adapter.setData(mList!!)
        })


    }



}

//set up data binding



//        //intilze data binding - this is replaced with requireActivity() if in a fragment
//        mBinding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_home)
//viewModel = ViewModelProviders.of(requireActivity()).get(FragmentViewModel::class.java)
//        mBinding.fragmentViewModel = viewModel
//        mBinding.lifecycleOwner = this