package com.example.kotlinfistapp.data.source

import android.app.Application
import com.example.kotlinfistapp.data.model.ItemToDo
import com.example.kotlinfistapp.data.model.ListeToDo
import com.example.kotlinfistapp.data.model.ProfilListeToDo
import com.example.kotlinfistapp.data.source.local.LocalDataSource
import com.example.kotlinfistapp.data.source.remote.RemoteDataSource
import java.lang.Exception

class ToDoRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
    )

{
    public fun refreshURL(newValue:String)
    {
        return remoteDataSource.refreshURL(newValue)
    }

    suspend fun authenticate(pseudo:String, mdp:String) : String
    {
        return remoteDataSource.authenticate(pseudo, mdp)
    }

    //Récupérer les listes de l'utilisateur connecté
    //Update la DB en conséquence
    suspend fun getListsOfTheUserFromAPI(hash:String): MutableList<ListeToDo> {
        return try {
            remoteDataSource.getListsOfTheUserFromAPI(hash).also {
                localDataSource.saveOrUpdateLists(it)
            }

        } catch (e: Exception) {
            localDataSource.getListsOfTheUserFromAPI()
        }
    }

    //Récupérer les items de la liste choisie par l'utilisateur (id)
    //Update la DB en conséquence

    suspend fun getItemOfTheList(id : String, hash: String): List<ItemToDo> {
        return try {
            remoteDataSource.getItemOfTheList(id, hash).also {
                localDataSource.saveOrUpdateItems(it)
            }

        } catch (e: Exception) {
            localDataSource.getItemOfTheList(id)
        }
    }

    fun changeItemInTheList(id: String, id1: String, value: String, toString: String) {
        TODO()
    }

    fun addItemInTheList(id: String, toString: String, toString1: String) {
        TODO()
    }

    companion object {
        fun newInstance(application: Application): ToDoRepository {
            return ToDoRepository(
                localDataSource = LocalDataSource(application),
                remoteDataSource = RemoteDataSource()
            )
        }
    }

}