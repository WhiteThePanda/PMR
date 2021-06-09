package com.example.kotlinfistapp.data.source.remote.api

import com.example.kotlinfistapp.data.model.ItemToDo
import com.example.kotlinfistapp.data.model.ListeToDo
import com.example.kotlinfistapp.data.model.ProfilListeToDo

data class APIResponse(
        val users : List<ProfilListeToDo>,
        val hash : String,
        val lists : Array<ListeToDo>,
        val items : Array<ItemToDo>)