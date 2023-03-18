package com.example.roomapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.roomapp.data.UserDatabase
import com.example.roomapp.model.University
import com.example.roomapp.repository.UniRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UniViewModel(application: Application): AndroidViewModel(application) {
    val readAllUniversity: LiveData<List<University>>
    private val repository: UniRepository
    init {
        val universityDao = UserDatabase.getDatabase(application).uniVersityDao()
        repository = UniRepository(universityDao)
        readAllUniversity = repository.readAllUniversity
    }

    fun addUniversity(university: University){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUniversity(university)
        }
    }

    fun updateUniversity(university: University){
        viewModelScope.launch (Dispatchers.IO){
            repository.updateUniversity(university)
        }
    }
}