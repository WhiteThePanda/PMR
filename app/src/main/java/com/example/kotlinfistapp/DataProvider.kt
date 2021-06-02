package com.example.kotlinfistapp
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataProvider {

    private var BASE_URL = "http://tomnab.fr/todo-api/"

    private var retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val service = retrofit.create(ToDoService::class.java)

    suspend fun getUsersFromAPI(hash : String): List<ProfilListeToDo> {
        return service.getUsers(hash).users
    }
    suspend fun authenticate(): String
    {
        return service.authenticate().hash
    }
    public fun refreshURL(newUrl : String)
    {
        BASE_URL=newUrl
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}