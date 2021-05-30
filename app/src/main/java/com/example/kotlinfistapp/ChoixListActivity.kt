@file:Suppress("DEPRECATION")

package com.example.kotlinfistapp

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.gson.Gson


class ChoixListActivity : AppCompatActivity(), View.OnClickListener{
    val TAG : String = "PMRMoi"
    var sp: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    var currentProfilListeToDo : ProfilListeToDo ?= null
    var edtList : EditText ?= null
    var listAdapter : ListRecyclerAdapter ?= ListRecyclerAdapter(this)
    val gson = Gson()
    var dataset: MutableList<ProfilListeToDo> = mutableListOf()
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
        currentProfilListeToDo = ProfilListeToDo(lastName)
        updateData()

    }

    private fun updateData() {
        var chaine_json : String = sp?.getString("data","")!!
        if(chaine_json=="")
        {
            val users = listOf<ProfilListeToDo>(ProfilListeToDo(lastName))
            chaine_json = gson.toJson(users)
        }
        Log.d("PMRMoi","inListActivity")
        Log.d("PMRMoi",chaine_json)
        dataset = gson.fromJson(chaine_json, Array<ProfilListeToDo>::class.java).toMutableList()
        var compteur : Int = 0
        for( profilListeToDo : ProfilListeToDo in dataset)
        {
            if(profilListeToDo.name == lastName)
            {
                compteur++
                currentProfilListeToDo = profilListeToDo
            }
        }
        if(compteur==0)
        {
            dataset.add(ProfilListeToDo(lastName))
            chaine_json = gson.toJson(dataset)
        }
        editor?.putString("data",chaine_json)
        editor?.commit()
        listAdapter?.initData(currentProfilListeToDo!!)
        recyclerView.adapter = listAdapter
        Log.d(TAG, dataset.toString())
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.buttonCreateList -> {
                updateData()
                currentProfilListeToDo?.GetMesListesToDo()?.add(ListeToDo(edtList?.text.toString()))
                val chaine_json = gson.toJson(dataset)
                editor?.putString("data",chaine_json)
                editor?.commit()
                listAdapter?.notifyDataSetChanged()
                }
        }
    }

}