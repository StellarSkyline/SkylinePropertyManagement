package com.example.skylinepropertymanagement.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.skylinepropertymanagement.R
import com.example.skylinepropertymanagement.data.model.Property
import kotlinx.android.synthetic.main.fragment_property_detail.*

class FragmentPropertyDetail: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_property_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var property = arguments?.getSerializable("data")!! as Property

        text_view_address.text = "${property.propertyaddress}, ${property.propertycity}"
        text_view_address2.text = "${property.propertystate} | ${property.propertycountry}"
        text_view_description.text = property.propertymortageinfo
        text_view_status.text = property.propertystatus



    }
}