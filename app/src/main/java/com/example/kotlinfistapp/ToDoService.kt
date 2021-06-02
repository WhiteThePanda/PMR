package com.example.kotlinfistapp

import android.telecom.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface ToDoService {
    @GET("users")
    suspend fun getUsers(): APIResponse
    @POST("authenticate?user=tom&password=web")
    suspend fun  authenticate(): AuthenticateResponse
}