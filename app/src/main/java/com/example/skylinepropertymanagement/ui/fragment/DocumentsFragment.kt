package com.example.skylinepropertymanagement.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.skylinepropertymanagement.R
import com.example.skylinepropertymanagement.app.App
import com.example.skylinepropertymanagement.app.log
import com.example.skylinepropertymanagement.app.toast
import com.example.skylinepropertymanagement.databinding.FragmentDocumentBinding
import kotlinx.android.synthetic.main.fragment_document.*

class DocumentsFragment: Fragment() {

        //initalize global variables
    lateinit var mBinding:FragmentDocumentBinding
    val viewModel: FragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //intilze data binding - this is replaced with requireActivity() if in a fragment
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_document, container, false)
        mBinding.fragmentViewModel = viewModel
        mBinding.lifecycleOwner = this

        return mBinding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.inputVal.observe(viewLifecycleOwner, Observer {
            when(it) {
                "name" -> {text_layout_name.error = "Document name required"}
                "type" -> {text_layout_type.error = "Document type is required"}
                "success" -> {
                    App.instance.toast("Document Saved in Database")
                }
            }
        })

    }
}