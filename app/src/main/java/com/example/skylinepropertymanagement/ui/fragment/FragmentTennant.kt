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
import com.example.skylinepropertymanagement.data.adapter.AdapterTennant
import com.example.skylinepropertymanagement.data.model.Tennant
import kotlinx.android.synthetic.main.fragment_tennant.*

class FragmentTennant: Fragment() {

    val viewModel: FragmentViewModel by activityViewModels()
    val navController by lazy {findNavController()}
    var mList:List<Tennant> = emptyList()
    lateinit var adapter:AdapterTennant

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tennant, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = AdapterTennant(activity?.applicationContext!!)
        recycler_view.layoutManager = LinearLayoutManager(activity?.applicationContext!!)
        recycler_view.adapter = adapter

        viewModel.getTennant()

        viewModel.repo.tennant.observe(viewLifecycleOwner, Observer {
            mList = it
            adapter.setData(mList)
        })
    }
}