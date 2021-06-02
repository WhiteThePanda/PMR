package com.example.kotlinfistapp

import retrofit2.http.GET
import retrofit2.http.PUT

interface ToDoService {
    @GET("users")
    suspend fun getUsers(): APIResponse
}