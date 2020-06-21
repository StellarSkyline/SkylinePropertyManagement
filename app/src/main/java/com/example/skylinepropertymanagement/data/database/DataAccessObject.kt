package com.example.skylinepropertymanagement.data.database

import androidx.room.Dao
import androidx.room.Insert
import com.example.skylinepropertymanagement.data.database.entity.SavedDocuments
import com.example.skylinepropertymanagement.data.model.Document


@Dao
interface DataAccessObject {

    @Insert
    fun addDocument(d:SavedDocuments)
}