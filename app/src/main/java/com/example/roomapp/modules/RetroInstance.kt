package com.example.roomapp.modules

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetroInstance {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    private val retrofit:Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    fun <T> createService(apiService: Class<T>):T{
        return retrofit.create(apiService)
    }
}