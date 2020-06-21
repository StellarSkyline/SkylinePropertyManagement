package com.example.skylinepropertymanagement.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.skylinepropertymanagement.data.database.entity.Homes
import com.example.skylinepropertymanagement.data.database.entity.SavedDocuments

@Database(entities = [SavedDocuments::class], version = 1)
abstract class DB:RoomDatabase() {

    abstract fun Dao():DataAccessObject

    companion object {
        @Volatile
        private var instance: DB? = null
        private val LOCK = Any()

        operator fun invoke(context:Context) = instance?: synchronized(LOCK) {
            instance?: createDatabase(context).also {
                instance = it
            }

        }

        fun createDatabase(context:Context) = Room.databaseBuilder(context.applicationContext, DB::class.java, "DB").allowMainThreadQueries().build()
    }
}