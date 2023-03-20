package com.example.roomapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomapp.model.University

@Dao
interface UniversityDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUniversity(university: University)

    @Query("SELECT *FROM university_table ORDER BY id ASC")
    fun readAllUniversity():LiveData<List<University>>

    @Update
    suspend fun updateUniversity(university: University)

    @Delete
    suspend fun deleteUniversity(university: University)
}