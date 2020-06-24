package com.example.skylinepropertymanagement.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.skylinepropertymanagement.R
import com.example.skylinepropertymanagement.data.model.Property
import kotlinx.android.synthetic.main.fragment_property_detail.*

class FragmentPropertyDetail: Fragment() {
    val navController by lazy {findNavController()}
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_property_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var property = arguments?.getSerializable("data") as Property

        text_view_address_pDetail.text = "${property.propertyaddress}, ${property.propertycity}"
        text_view_address2_pDetail.text = "${property.propertystate} | ${property.propertycountry}"
        text_view_description_pDetail.text = property.propertymortageinfo

        button_add_tennant.setOnClickListener {
            val bundle = bundleOf("id" to property)

            navController.navigate(R.id.action_fragmentPropertyDetail_to_fragmentAddTenant, bundle)

        }




    }
}