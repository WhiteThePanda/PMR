package com.example.kotlinfistapp.data.source.remote.api

import com.example.kotlinfistapp.data.source.remote.api.APIResponse
import retrofit2.http.*


interface ToDoService {
    @GET("users")
    suspend fun getUsers(@Query("hash") hashText : String): APIResponse
    @POST("authenticate")
    suspend fun  authenticate(@Query("user") user : String,@Query("password") mdp : String): APIResponse
    @GET("lists")
    suspend fun getListsOfTheUser(@Query("hash") hashText : String): APIResponse
    @GET("lists/{id}/items")
    suspend fun getItemOfTheList(@Path("id") id : String, @Query("hash") hashText : String) : APIResponse
    @POST("lists/{id}/items")
    suspend fun addItemInList(@Path("id") id : String, @Query("label")label:String, @Query("hash") hashText : String)
    @PUT("lists/{idList}/items/{idItem}")
    suspend fun changeItemInList(@Path("idList") idList : String,@Path("idItem") idItem : String,@Query("check") checkedText : String,@Query("hash") hashText : String)
}