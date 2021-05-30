@file:Suppress("DEPRECATION")

package com.example.kotlinfistapp
import android.content.ClipData
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
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.json.JSONException
import org.json.JSONObject


class ShowListActivity : AppCompatActivity(), View.OnClickListener{
    var sp: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    var edtItem : EditText?= null
    var username : String = ""
    lateinit var itemAdapter : ItemRecyclerAdapter
    val gson = Gson()
    var dataset: MutableList<User> = mutableListOf()
    var position : Int = 0
    var listOfItem : MutableList<ItemToDo> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_list_activity)
        sp = PreferenceManager.getDefaultSharedPreferences(this)
        edtItem = findViewById(R.id.newItem)
        editor = sp?.edit()
        findViewById<Button>(R.id.buttonCreateItem).setOnClickListener(this)
        val recyclerView = findViewById<RecyclerView>(R.id.itemRecyclerView)
        recyclerView.layoutManager= LinearLayoutManager(this)
        username = this.intent.getStringExtra("name").toString()
        position = this.intent.getIntExtra("position",0)
        var chaine_json : String = sp?.getString("data","")!!
        if(chaine_json=="")
        {
            val users = listOf<User>(User(getString(R.string.defaultPseudo)))
            chaine_json = gson.toJson(users)
        }
        Log.d("PMRMoi","inActivity")
        Log.d("PMRMoi",chaine_json)
        dataset = gson.fromJson(chaine_json, Array<User>::class.java).toMutableList()
        for( user : User in dataset)
        {
            if(user.name == username)
            {
                listOfItem = user.toDoLists[position].lesItems
            }
        }
        itemAdapter= ItemRecyclerAdapter(this,dataset,username,position)
        recyclerView.adapter = itemAdapter
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.buttonCreateItem -> {
                listOfItem.add(ItemToDo(edtItem?.text.toString()))
                val chaine_json = gson.toJson(dataset)
                Log.d("PMRMoi",chaine_json)
                editor?.putString("data",chaine_json)
                editor?.commit()
                itemAdapter?.notifyDataSetChanged()
            }
            R.id.itemcb->{

            }
        }
    }

}