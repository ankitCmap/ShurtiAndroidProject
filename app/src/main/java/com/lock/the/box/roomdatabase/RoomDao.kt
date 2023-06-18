package com.lock.the.box.roomdatabase

import androidx.room.*

@Dao
interface RoomDao {
    @Query("SELECT * FROM RoomModel")
    fun getAll(): List<RoomModel>

    @Insert
    fun insertAll(Courses: List<RoomModel>)

    @Insert
    fun insert(note: RoomModel)

    @Update
    fun update(note: RoomModel)

    @Delete
    fun delete(note: RoomModel)

    @Query("delete from RoomModel")
    fun deleteAllNotes()
}