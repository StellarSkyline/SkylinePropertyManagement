package com.example.skylinepropertymanagement.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.skylinepropertymanagement.R
import com.example.skylinepropertymanagement.data.model.Meeting
import com.example.skylinepropertymanagement.ui.fragment.MeetingFragment
import kotlinx.android.synthetic.main.adapter_row_meeting.view.*

class AdapterMeeting(var mContext: Context):RecyclerView.Adapter<AdapterMeeting.ViewHolder>() {

    var mList:List<Meeting> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(mContext).inflate(R.layout.adapter_row_meeting, parent, false)
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

        fun bindView(item:Meeting) {
            itemView.image_view.setImageResource(R.drawable.ic_tick)
            itemView.text_view_name.text = item.name

        }
    }

    fun setData(list:List<Meeting>) {
        mList = list
        notifyDataSetChanged()
    }
}