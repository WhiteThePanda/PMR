package com.example.kotlinfistapp.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kotlinfistapp.data.model.ProfilListeToDo

@Database(
    entities = [
        ProfilListeToDo::class
    ],
    version = 1
)
abstract class ProductHuntRoomDatabase : RoomDatabase() {

    abstract fun todoDao(): PostDao
}