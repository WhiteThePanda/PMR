package com.example.kotlinfistapp

import android.telecom.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface ToDoService {
    @GET("users")
    suspend fun getUsers(@Query("hash") hashText : String): APIResponse
    @POST("authenticate?user=tom&password=web")
    suspend fun  authenticate(): APIResponse
}