package com.example.skylinepropertymanagement.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "homes")
data class Homes(
    @PrimaryKey(autoGenerate = true)
    var id:Int
)

