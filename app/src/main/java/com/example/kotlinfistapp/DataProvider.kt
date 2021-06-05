package com.example.kotlinfistapp
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.provider.Settings.Global.getString
import android.util.Log
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataProvider {

    private var BASE_URL = "http://tomnab.fr/todo-api/"

    private var retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    private var service = retrofit.create(ToDoService::class.java)

    suspend fun getUsersFromAPI(hash : String): MutableList<ProfilListeToDo> {
        return service.getUsers(hash).users.toMutableList()
    }
    suspend fun getListsOfTheUserFromAPI(hash : String): MutableList<ListeToDo> {
        return service.getListsOfTheUser(hash).lists.toMutableList()
    }
    suspend fun authenticate(pseudo : String,mdp :String): String
    {
        return service.authenticate(pseudo,mdp).hash
    }
    suspend fun getItemOfTheList(id : String,hash: String) : MutableList<ItemToDo>
    {
        return service.getItemOfTheList(id,hash).items.toMutableList()
    }
    suspend fun addItemInTheList(id:String,label : String,hash: String)
    {
        service.addItemInList(id,label,hash)
    }
    suspend fun changeItemInTheList(idList : String, idItem : String , checked : String, hash : String)
    {
        service.changeItemInList(idList,idItem,checked,hash)
    }

    public fun refreshURL(newUrl : String)
    {
        BASE_URL = newUrl
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(ToDoService::class.java)
    }

}