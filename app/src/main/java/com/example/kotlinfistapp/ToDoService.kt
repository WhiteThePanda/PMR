package com.example.kotlinfistapp

import android.telecom.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT

interface ToDoService {
    @GET("users?hash=c0c19cb530d87fff0b90d71a1a4863e8")
    suspend fun getUsers(): APIResponse
    @GET("lists?hash=c0c19cb530d87fff0b90d71a1a4863e8")
    suspend fun getListOfActuelUser() : List<ListeToDo>
    @POST("authenticate?user=tom&password=web")
    suspend fun  authenticate(): APIResponse

}