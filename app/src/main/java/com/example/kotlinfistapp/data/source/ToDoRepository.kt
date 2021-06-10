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

    //Coche ou décoche un item
    //Update la DB en conséquence
    suspend fun changeItemInTheList(idList : String, idItem : String , checked : String, hash : String) {
        return try {
            remoteDataSource.changeItemInTheList(idList, idItem, checked, hash).also {
                localDataSource.saveOrUpdateItemInTheList(it)
            }
        } catch (e: Exception) {
            localDataSource.changeItemInTheList(idList, idItem, checked)
        }
    }

    //Ajoute un item dans la liste
    //Update la DB en conséquence
    suspend fun addItemInTheList(id:String,label : String,hash: String) {
        return try {
            remoteDataSource.addItemInTheList(id,label,hash).also {
                localDataSource.saveOrUpdateItemInTheList(it)
            }
        } catch (e: Exception) {
            localDataSource.addItemInTheList(id, label)
        }
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