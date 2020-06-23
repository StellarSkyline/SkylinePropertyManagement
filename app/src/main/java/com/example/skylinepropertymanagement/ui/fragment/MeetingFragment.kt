package com.example.skylinepropertymanagement.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.example.skylinepropertymanagement.data.adapter.AdapterMeeting
import com.example.skylinepropertymanagement.data.model.Meeting
import kotlinx.android.synthetic.main.fragment_meeting.*

class MeetingFragment: Fragment() {
    val navController by lazy {findNavController()}
    val viewModel: FragmentViewModel by activityViewModels()
    lateinit var adapter: AdapterMeeting
    var mList:List<Meeting> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_meeting,container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter= AdapterMeeting(activity?.applicationContext!!)
        recycler_view.layoutManager = LinearLayoutManager(activity?.applicationContext!!)
        recycler_view.adapter = adapter
        mList = viewModel.repo.getMeeting()
        adapter.setData(mList)


        button_add_meeting.setOnClickListener {
            navController.navigate(R.id.action_meetingFragment_to_addMeetingFragment)
        }

        viewModel.repo.meetingData.observe(viewLifecycleOwner, Observer {
            //Observe Meeting Data here and add to recycler view
            mList = it!!
            App.instance.log(mList.toString())
            adapter.setData(mList!!)

        })

        Jump.DELETE_MEETING_TRIGGER.onlyNew(this).observe(viewLifecycleOwner, Observer {
            viewModel.repo.deleteMeeting(it)
            mList = viewModel.repo.getMeeting()
            adapter.setData(mList)

        })


    }
}