package com.example.skylinepropertymanagement.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.skylinepropertymanagement.data.database.entity.MeetingTable
import com.example.skylinepropertymanagement.data.database.entity.SavedDocuments
import com.example.skylinepropertymanagement.data.model.Document
import com.example.skylinepropertymanagement.data.model.Meeting


@Dao
interface DataAccessObject {

    @Insert()
    fun addDocument(d:SavedDocuments)

    @Insert
    fun addMeeting(m:MeetingTable)

    @Query("select * from meetingTable")
    fun getMeeting():List<Meeting>

    @Query("delete from meetingTable where id = :id ")
    fun deleteMeeting(id:String)


}