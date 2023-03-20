package com.example.roomapp.services

import com.example.roomapp.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET
    fun getUserFromOnline(
        @Url url:String
    ):Call<UserResponse>
}