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
import com.example.skylinepropertymanagement.databinding.FragmentAddMeetingBinding
import kotlinx.android.synthetic.main.fragment_add_meeting.*

class AddMeetingFragment:Fragment() {
    lateinit var mBinding: FragmentAddMeetingBinding
    val viewModel: FragmentViewModel by activityViewModels()
    val navController by lazy {findNavController()}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_meeting, container, false)
        mBinding.fragmentViewModel = viewModel
        mBinding.lifecycleOwner = this

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.inputVal.observe(viewLifecycleOwner, Observer {
            when(it) {
                "name" -> {text_layout_name.error = "Meeting name is required"}
                "location"->{text_input_location.error = "Location is required"}
                "time" -> {text_input_time.error = "Time is required"}
                "success" -> {
                    text_layout_name.isErrorEnabled = false
                    text_input_location.isErrorEnabled = false
                    text_input_time.isErrorEnabled = false
                    navController.navigate(R.id.action_addMeetingFragment_to_meetingFragment)
                }
            }
        })
    }
}