package com.example.kotlinfistapp

import com.google.gson.annotations.SerializedName

data class APIResponse(
    @SerializedName("users")
    val profilListesToDo : List<ProfilListeToDo>)