@file:Suppress("DEPRECATION")

package com.example.kotlinfistapp

import android.content.SharedPreferences
import android.nfc.Tag
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.json.JSONException
import org.json.JSONObject


class ChoixListActivity : AppCompatActivity(), View.OnClickListener{
    val TAG : String = "PMRMoi"
    var sp: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    var currentUser : User ?= null
    var edtList : EditText ?= null
    var listAdapter : ListRecyclerAdapter ?= ListRecyclerAdapter(this)
    val gson = Gson()
    var dataset: MutableList<User> = mutableListOf()
    lateinit var recyclerView : RecyclerView
    lateinit var lastName : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_layout)
        sp = PreferenceManager.getDefaultSharedPreferences(this)
        edtList = findViewById(R.id.newList)
        editor = sp?.edit()
        findViewById<Button>(R.id.buttonCreateList).setOnClickListener(this)
        recyclerView = findViewById<RecyclerView>(R.id.listRecyclerView)
        recyclerView.layoutManager=LinearLayoutManager(this)
        val bdl = this.intent.extras
        lastName = bdl!!.getString("pseudo",getString(R.string.defaultPseudo))
        currentUser = User(lastName)
        updateData()

    }

    private fun updateData() {
        var chaine_json : String = sp?.getString("data","")!!
        if(chaine_json=="")
        {
            val users = listOf<User>(User(lastName))
            chaine_json = gson.toJson(users)
        }
        Log.d("PMRMoi","inListActivity")
        Log.d("PMRMoi",chaine_json)
        dataset = gson.fromJson(chaine_json, Array<User>::class.java).toMutableList()
        var compteur : Int = 0
        for( user : User in dataset)
        {
            if(user.name == lastName)
            {
                compteur++
                currentUser = user
            }
        }
        if(compteur==0)
        {
            dataset.add(User(lastName))
            chaine_json = gson.toJson(dataset)
        }
        editor?.putString("data",chaine_json)
        editor?.commit()
        listAdapter?.initData(currentUser!!)
        recyclerView.adapter = listAdapter
        Log.d(TAG, dataset.toString())
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.buttonCreateList -> {
                updateData()
                currentUser?.toDoLists?.add(ListeToDo(edtList?.text.toString()))
                val chaine_json = gson.toJson(dataset)
                editor?.putString("data",chaine_json)
                editor?.commit()
                listAdapter?.notifyDataSetChanged()
                }
        }
    }

}