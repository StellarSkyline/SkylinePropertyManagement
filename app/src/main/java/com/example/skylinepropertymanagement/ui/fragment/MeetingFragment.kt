package com.example.skylinepropertymanagement.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.skylinepropertymanagement.R
import kotlinx.android.synthetic.main.fragment_meeting.*

class MeetingFragment: Fragment() {
    val navController by lazy {findNavController()}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_meeting,container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_add_meeting.setOnClickListener {
            navController.navigate(R.id.action_meetingFragment_to_addMeetingFragment)
        }
    }
}