package com.example.kotlinfistapp

import com.google.gson.annotations.SerializedName

data class APIResponse(
    val users : List<ProfilListeToDo>,
    val hash : String,
    val lists : Array<ListeToDo>,
    val items : Array<ItemToDo>)