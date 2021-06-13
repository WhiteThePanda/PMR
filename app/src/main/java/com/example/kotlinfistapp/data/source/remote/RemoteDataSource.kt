package com.example.kotlinfistapp.data.source.remote
import android.util.Log
import com.example.kotlinfistapp.data.source.remote.api.ToDoService
import com.example.kotlinfistapp.data.model.ProfilListeToDo
import com.example.kotlinfistapp.data.model.ItemToDo
import com.example.kotlinfistapp.data.model.ListeToDo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

class RemoteDataSource {

    private var BASE_URL = "http://tomnab.fr/todo-api/"

    private var retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    private var service = retrofit.create(ToDoService::class.java)

    suspend fun getUsersFromAPI(hash : String): MutableList<ProfilListeToDo> {
        return service.getUsers(hash).users.toMutableList()
    }

    suspend fun authenticate(pseudo : String,mdp :String): String
    {
        return service.authenticate(pseudo,mdp).hash
    }

    suspend fun getListsOfTheUserFromAPI(hash : String): MutableList<ListeToDo> {
        return service.getListsOfTheUser(hash).lists.toMutableList()
    }

    suspend fun getItemOfTheList(id : String,hash: String) : MutableList<ItemToDo>
    {
        val items = service.getItemOfTheList(id,hash).items.toMutableList()
        for(item in items)
        {
            item.isSync=true;
        }
        return items
    }
    suspend fun addItemInTheList(id:String,label : String,hash: String)
    {
         service.addItemInList(id,label,hash)
    }
    suspend fun changeItemInTheList(idList : String, idItem : String , checked : String, hash : String)
    {
        Log.d("PMRMoi"," hash qui marche")
        Log.d("PMRMoi",hash)
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

    suspend fun syncItems(itemsNonSync: MutableList<ItemToDo>,hash : String) {

        for(item in itemsNonSync)
        {
            try {
                service.changeItemInList(item.idList,item.id,item.faitText,hash)
            }
            catch (e : Exception)
            {
                Log.d("PMRMoi",e.toString())
            }
            item.isSync=true
        }
    }

}