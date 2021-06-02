package com.example.kotlinfistapp

import com.google.gson.annotations.SerializedName

data class APIResponse(
    val users : List<ProfilListeToDo>,
    val hash : String)