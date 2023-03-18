package com.example.roomapp.repository

import androidx.lifecycle.LiveData
import com.example.roomapp.data.UniversityDao
import com.example.roomapp.model.University

class UniRepository(private val universityDao: UniversityDao) {
    val readAllUniversity: LiveData<List<University>> = universityDao.readAllUniversity()

    suspend fun addUniversity(university: University){
        universityDao.addUniversity(university)
    }

    suspend fun updateUniversity(university: University){
        universityDao.updateUniversity(university)
    }
}