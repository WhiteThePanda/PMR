package com.example.kotlinfistapp.data.source

import android.app.Application
import com.example.kotlinfistapp.data.model.ItemToDo
import com.example.kotlinfistapp.data.model.ListeToDo
import com.example.kotlinfistapp.data.model.ProfilListeToDo
import com.example.kotlinfistapp.data.source.local.LocalDataSource
import com.example.kotlinfistapp.data.source.remote.RemoteDataSource
import java.lang.Exception

class ToDoRepository(
    private val hash: String,
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
    )

{
    //Récupérer les listes de l'utilisateur connecté
    //Update la DB en conséquence
    suspend fun getListsOfTheUserFromAPI(): List<ListeToDo> {
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

    suspend fun getItemOfTheList(id : String): List<ItemToDo> {
        return try {
            remoteDataSource.getItemOfTheList(id, hash).also {
                localDataSource.saveOrUpdateItems(it)
            }

        } catch (e: Exception) {
            localDataSource.getItemOfTheList(id)
        }
    }


    companion object {
        fun newInstance(application: Application): PostRepository {
            return PostRepository(
                localDataSource = LocalDataSource(application),
                remoteDataSource = RemoteDataSource()
            )
        }
    }

}