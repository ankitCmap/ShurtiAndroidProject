package com.lock.the.box.di

import android.content.Context
import androidx.room.Room
import com.lock.the.box.roomdatabase.AppDatabase
import com.lock.the.box.roomdatabase.RoomDao
import com.lock.the.box.utils.constant.DbConstant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun getDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, DbConstant.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }


    @Singleton
    @Provides
    fun provideRoomDao(appDatabase: AppDatabase): RoomDao = appDatabase.mainPartDeo()

}