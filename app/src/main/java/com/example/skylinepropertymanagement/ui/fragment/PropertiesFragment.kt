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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.skylinepropertymanagement.R
import com.example.skylinepropertymanagement.app.App
import com.example.skylinepropertymanagement.app.Jump
import com.example.skylinepropertymanagement.app.log
import com.example.skylinepropertymanagement.app.onlyNew
import com.example.skylinepropertymanagement.data.adapter.AdapterProperties
import com.example.skylinepropertymanagement.data.model.Property
import com.example.skylinepropertymanagement.databinding.FragmentPropertiesBinding
import kotlinx.android.synthetic.main.fragment_home.*

class PropertiesFragment: Fragment() {
    lateinit var mBinding: FragmentPropertiesBinding
    val viewModel: FragmentViewModel by activityViewModels()
    lateinit var adapter: AdapterProperties
    val navController by lazy {findNavController()}
    var mList:List<Property>? = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_properties, container, false)
        mBinding.fragmentViewModel = viewModel
        mBinding.lifecycleOwner = this

        adapter = AdapterProperties(activity?.applicationContext!!)

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_view.layoutManager = LinearLayoutManager(activity?.applicationContext)
        recycler_view.adapter = adapter
        viewModel.repo.getPropertyList()

        viewModel.repo.propertyData.observe(viewLifecycleOwner, Observer{
            mList = it!!
            App.instance.log(mList.toString())
            adapter.setData(mList!!)
        })


        viewModel.checkJump.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                navController.navigate(R.id.action_propertiesFragment_to_addProperty)
            }
        })

        Jump.DELETE_PROP_TRIGGER.onlyNew(this).observe(viewLifecycleOwner, Observer {
            viewModel.repo.deleteProperty(it)
            viewModel.repo.getPropertyList()
        })

    }

//    override fun removeProperty(propertyid: String) {
//        viewModel.repo.deleteProperty(propertyid)
//    }

}