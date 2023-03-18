package com.example.roomapp.repository

import androidx.lifecycle.LiveData
import com.example.roomapp.data.UserDao
import com.example.roomapp.model.User
import com.example.roomapp.network.ApiInterface
import javax.inject.Inject


class UserRepository @Inject constructor(private val userDao: UserDao, private val api:ApiInterface) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User){
        userDao.deleteUser(user)
    }

    suspend fun deleteAllUsers(){
        userDao.deleteAllUsers()
    }



}