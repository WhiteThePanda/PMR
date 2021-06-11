@file:Suppress("DEPRECATION")

package com.example.kotlinfistapp.ui

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager

import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinfistapp.*
import com.example.kotlinfistapp.data.model.ListeToDo
import com.example.kotlinfistapp.data.source.ToDoRepository
import com.example.kotlinfistapp.data.source.remote.RemoteDataSource
import com.example.kotlinfistapp.ui.adapter.ListRecyclerAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ChoixListActivity : AppCompatActivity(), View.OnClickListener, ListRecyclerAdapter.ActionListener {
    private val activityScope = CoroutineScope(Dispatchers.IO)
    var sp: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    var edtList : EditText ?= null
    lateinit var mesListes : MutableList<ListeToDo>
    lateinit var listAdapter : ListRecyclerAdapter
    lateinit var recyclerView : RecyclerView
    private val toDoRepository by lazy { ToDoRepository.newInstance(application) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_layout)
        sp = PreferenceManager.getDefaultSharedPreferences(this)
        edtList = findViewById(R.id.newList)
        editor = sp?.edit()
        recyclerView = findViewById<RecyclerView>(R.id.listRecyclerView)
        recyclerView.layoutManager= LinearLayoutManager(this)
        listAdapter = ListRecyclerAdapter(this)
        recyclerView.adapter = listAdapter
        findViewById<Button>(R.id.buttonCreateList).setOnClickListener(this)
        updateData()
    }

    private fun updateData() {

        activityScope.launch {
            try {
                val hash = sp?.getString("hash","")
                mesListes = toDoRepository.getListsOfTheUserFromAPI(hash!!,intent.getStringExtra("pseudo").toString())
                RefreshRecyclerOnMainThread()
                Log.d("PMRMoi",mesListes.toString())
            } catch (e: Exception) {
                Log.d("PMRMoi",e.toString())
            }
        }
    }
    private suspend fun RefreshRecyclerOnMainThread()
    {
        withContext(Main){
            RefreshRecycler()
        }
    }
    private fun RefreshRecycler()
    {
        listAdapter?.initData(mesListes)
        listAdapter.notifyDataSetChanged()
    }
    override fun onClick(v: View) {
        when (v.id) {
            R.id.buttonCreateList -> {
                updateData()
                }
        }
    }

    override fun onItemClicked(position: Int) {
        val toItemAct: Intent = Intent(this, ShowListActivity::class.java)
        toItemAct.putExtra("id",mesListes[position].id)
        startActivity(toItemAct)
    }

}