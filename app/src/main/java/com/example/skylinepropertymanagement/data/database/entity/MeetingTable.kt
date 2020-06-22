package com.example.skylinepropertymanagement.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "meetingTable")
data class MeetingTable(
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    var name:String,
    var location:String,
    var time:String,
    var description:String
)