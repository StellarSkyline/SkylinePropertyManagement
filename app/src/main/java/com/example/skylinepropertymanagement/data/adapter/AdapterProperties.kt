package com.example.skylinepropertymanagement.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.skylinepropertymanagement.R
import com.example.skylinepropertymanagement.data.model.Property
import kotlinx.android.synthetic.main.adapter_row_property_layout.view.*

class AdapterProperties(var mContext:Context):RecyclerView.Adapter<AdapterProperties.ViewHolder>() {

    var mList:List<Property> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(mContext).inflate(R.layout.adapter_row_property_layout, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item = mList[position]
        holder.bindView(item)

    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        fun bindView(item:Property) {
            itemView.text_view_address.text = item.propertyaddress
            itemView.text_view_city.text = item.propertycity
            itemView.text_view_price.text = item.propertypurchaseprice

        }

    }

    fun setData(list:List<Property>) {
        mList = list
        notifyDataSetChanged()

    }
}