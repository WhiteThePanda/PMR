@file:Suppress("DEPRECATION")

package com.example.kotlinfistapp.ui
import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinfistapp.data.source.remote.RemoteDataSource
import com.example.kotlinfistapp.ui.adapter.ItemRecyclerAdapter
import com.example.kotlinfistapp.data.model.ItemToDo
import com.example.kotlinfistapp.R
import com.example.kotlinfistapp.data.source.ToDoRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main


class ShowListActivity : AppCompatActivity(), View.OnClickListener , ItemRecyclerAdapter.ActionListener {

    var sp: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    var edtItem : EditText?= null
    var id : String = ""
    var APIBool : Boolean = true;
    lateinit var hash : String
    private lateinit var itemAdapter : ItemRecyclerAdapter
    var listOfItem : MutableList<ItemToDo> = mutableListOf()
    lateinit var recyclerView : RecyclerView
    private val activityScope = CoroutineScope(Dispatchers.IO)
    private val toDoRepository by lazy {ToDoRepository.newInstance(application)}


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
            hash = sp?.getString("hash","").toString()
            syncData()
            listOfItem.addAll(toDoRepository.getItemOfTheList(id, hash))
            RefreshRecyclerOnMainThread()
        }
    }

    override fun onResume() {
        super.onResume()
        if(!APIBool)
        {
            Log.d("PMRMoi","showListActivity On Resume")
            activityScope.launch {
                syncData()
            }
        }
    }
    suspend fun syncData()
    {
        if(verifReseau())
        {
            APIBool=true;
            Log.d("PMRMoi","syncData")
            toDoRepository.syncWithAPI(hash)
            withContext(Main)
            {
                RefreshRecyclerOnMainThread()
            }
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
                        toDoRepository.addItemInTheList(id, edtItem?.text.toString(), hash.toString())
                        listOfItem.clear()
                        listOfItem.addAll(toDoRepository.getItemOfTheList(id, hash.toString()))
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
                toDoRepository.changeItemInTheList(id, listOfItem[position].id, value, hash.toString())
                val temp = toDoRepository.getItemOfTheList(id, hash.toString())
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
    private fun verifReseau(): Boolean {
        // On vérifie si le réseau est disponible,
        // si oui on change le statut du bouton de connexion
        val cnMngr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cnMngr.activeNetworkInfo
        var sType = "Aucun réseau détecté"
        var bStatut = false
        if (netInfo != null) {
            val netState = netInfo.state
            if (netState.compareTo(NetworkInfo.State.CONNECTED) == 0) {
                bStatut = true
                val netType = netInfo.type
                when (netType) {
                    ConnectivityManager.TYPE_MOBILE -> sType = "Réseau mobile détecté"
                    ConnectivityManager.TYPE_WIFI -> sType = "Réseau wifi détecté"
                }
            }
        }

        return bStatut
    }

}