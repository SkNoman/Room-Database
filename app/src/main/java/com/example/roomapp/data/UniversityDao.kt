package com.example.roomapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.roomapp.model.University

@Dao
interface UniversityDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUniversity(university: University)

    @Query("SELECT *FROM university_table ORDER BY id ASC")
    fun readAllUniversity():LiveData<List<University>>

    @Update
    fun updateUniversity(university: University)
}