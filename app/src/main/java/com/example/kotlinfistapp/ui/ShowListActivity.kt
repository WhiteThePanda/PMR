@file:Suppress("DEPRECATION")

package com.example.kotlinfistapp.ui
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
import com.example.kotlinfistapp.data.source.remote.RemoteDataSource
import com.example.kotlinfistapp.ui.adapter.ItemRecyclerAdapter
import com.example.kotlinfistapp.data.model.ItemToDo
import com.example.kotlinfistapp.R
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main


class ShowListActivity : AppCompatActivity(), View.OnClickListener , ItemRecyclerAdapter.ActionListener {
    var sp: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    var edtItem : EditText?= null
    var id : String = ""
    var APIBool : Boolean = true;
    private lateinit var itemAdapter : ItemRecyclerAdapter
    var listOfItem : MutableList<ItemToDo> = mutableListOf()
    lateinit var recyclerView : RecyclerView
    private val activityScope = CoroutineScope(Dispatchers.IO)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_list_activity)
        sp = PreferenceManager.getDefaultSharedPreferences(this)
        edtItem = findViewById(R.id.newItem)
        editor = sp?.edit()
        findViewById<Button>(R.id.buttonCreateItem).setOnClickListener(this)
        recyclerView = findViewById<RecyclerView>(R.id.itemRecyclerView)
        recyclerView.layoutManager= LinearLayoutManager(this)
        itemAdapter= ItemRecyclerAdapter(this@ShowListActivity, listOfItem)
        recyclerView.adapter = itemAdapter
        id = this.intent.getStringExtra("id").toString()
        activityScope.launch {
            val hash = sp?.getString("hash","")
            Log.d("PMRMoi", id)
            listOfItem.addAll(RemoteDataSource.getItemOfTheList(id, hash.toString()))
            RefreshRecyclerOnMainThread()
        }
    }
    private suspend fun RefreshRecyclerOnMainThread()
    {
        withContext(Main){
            itemAdapter.notifyDataSetChanged()
            APIBool = false;
        }
    }
    override fun onClick(v: View) {
        when (v.id) {
            R.id.buttonCreateItem -> {
                if(!APIBool)
                {
                    APIBool = true;
                    activityScope.launch {
                        val hash = sp?.getString("hash","")
                        RemoteDataSource.addItemInTheList(id, edtItem?.text.toString(), hash.toString())
                        listOfItem.clear()
                        listOfItem.addAll(RemoteDataSource.getItemOfTheList(id, hash.toString()))
                        withContext(Main)
                        {
                            Log.d("PMRMoi","test")
                            itemAdapter?.notifyDataSetChanged()
                            APIBool=false;
                        }
                    }
                }
            }
        }
    }

    override fun onItemClicked(position: Int) {
        if(!APIBool)
        {
            APIBool = true;
            Log.d("PMRMoi",listOfItem[position].faitText)
            activityScope.launch {

                val hash = sp?.getString("hash","")
                var value : String = "0"
                if(listOfItem[position].faitText=="0")value = "1"
                RemoteDataSource.changeItemInTheList(id, listOfItem[position].id, value, hash.toString())
                val temp = RemoteDataSource.getItemOfTheList(id, hash.toString())
                withContext(Main)
                {
                    listOfItem.clear()
                    listOfItem.addAll(temp)
                    itemAdapter.notifyItemRangeChanged(position, listOfItem.size)
                    itemAdapter.notifyDataSetChanged()
                    APIBool=false;
                }
            }
        }
    }

}