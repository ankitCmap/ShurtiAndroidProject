package com.example.shurtiandroidproject.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RoomModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mainPartDeo(): RoomDao
    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDataseClient(context: Context) : AppDatabase {
            if (INSTANCE != null) return INSTANCE!!
            synchronized(this) {
                INSTANCE = Room
                    .databaseBuilder(context, AppDatabase::class.java, "SHURTI_DATABASE")
                    .fallbackToDestructiveMigration()
                    .build()
                return INSTANCE!!

            }
        }
    }
}
