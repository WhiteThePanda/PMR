package com.example.kotlinfistapp.data.source.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kotlinfistapp.data.model.ItemToDo
import com.example.kotlinfistapp.data.model.ListeToDo
import com.example.kotlinfistapp.data.model.ProfilListeToDo
import com.example.kotlinfistapp.data.source.local.database.dbItemToDo.ItemToDoDao
import com.example.kotlinfistapp.data.source.local.database.dbListeToDo.ListeToDoDao
import com.example.kotlinfistapp.data.source.local.database.dbProfilListeToDo.ProfilListeToDoDao

@Database(
    entities = [
        ProfilListeToDo::class,
        ListeToDo::class,
        ItemToDo::class
    ],
    version = 1
)

abstract class ToDoDatabase : RoomDatabase() {
    abstract fun profilListeToDoDao(): ProfilListeToDoDao
    abstract fun listeToDoDao(): ListeToDoDao
    abstract fun itemToDoDao(): ItemToDoDao
}