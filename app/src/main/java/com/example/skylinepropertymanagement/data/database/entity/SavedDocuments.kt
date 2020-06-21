package com.example.skylinepropertymanagement.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Documents")
data class SavedDocuments(
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    var name:String,
    var type:String

)