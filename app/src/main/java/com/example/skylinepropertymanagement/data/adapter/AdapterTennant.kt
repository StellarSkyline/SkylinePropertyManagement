package com.example.skylinepropertymanagement.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.skylinepropertymanagement.R
import com.example.skylinepropertymanagement.data.model.Tennant
import kotlinx.android.synthetic.main.adapter_row_tennant.view.*

class AdapterTennant(var mContext:Context):RecyclerView.Adapter<AdapterTennant.ViewHolder>() {
    var mList:List<Tennant> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(mContext).inflate(R.layout.adapter_row_tennant, parent, false)

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
        fun bindView(item:Tennant) {
            itemView.image_view_house.setImageResource(R.drawable.img_house)
            itemView.text_view_name.text = item.tenantname
            itemView.text_view_address.text = item.tenantaddress
            itemView.text_view_contact.text = "${item.tenantemail} | ${item.tenantmobile}"
        }
    }

    fun setData(list:List<Tennant>) {
        mList = list
        notifyDataSetChanged()
    }
}