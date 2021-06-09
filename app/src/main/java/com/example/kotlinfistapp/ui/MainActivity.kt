@file:Suppress("DEPRECATION")

package com.example.kotlinfistapp.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.example.kotlinfistapp.data.source.remote.RemoteDataSource
import com.example.kotlinfistapp.R
import com.example.kotlinfistapp.data.source.ToDoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val activityScope = CoroutineScope(IO)
    var sp: SharedPreferences? = null
    var editor: Editor? = null
    var btnOk: Button? = null
    var checkBox: CheckBox? = null
    var etPseudo: EditText? = null
    var etMdp: EditText? = null
    private val toDoRepository by lazy { ToDoRepository.newInstance(application) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sp = PreferenceManager.getDefaultSharedPreferences(this)
        editor = sp?.edit()
        btnOk = findViewById(R.id.button)
        checkBox = findViewById(R.id.checkBox)
        etPseudo = findViewById(R.id.pseudo)
        etMdp = findViewById(R.id.edtmdp)
        btnOk?.setOnClickListener(this)
        findViewById<View>(R.id.pseudo).setOnClickListener(this)
        checkBox?.setOnClickListener(this)
        editor!!.putString("urlData", getString(R.string.url))
        editor!!.commit()
        Log.d(TAG, "OnCreate")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.menu_settings) {
            val myToast: Toast = Toast.makeText(this, "preference", Toast.LENGTH_LONG)
            myToast.show()
            val toPrefAct: Intent = Intent(this@MainActivity, GestionPreferences::class.java)
            startActivity(toPrefAct)
        }
        if(id == R.id.menu_network){
            val iReseau = Intent(this, Reseau::class.java)
            startActivity(iReseau)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "OnStart")

        // Permet de réinitialiser l'url par défaut dans les préférences partagées.

        val cbR = sp!!.getBoolean("remember", true)


        checkBox!!.isChecked = cbR
        if (checkBox!!.isChecked) {
            val l = sp!!.getString("login", getString(R.string.defaultPseudo))
            etPseudo!!.setText(l)
        } else {
            etPseudo!!.setText("")
        }
    }

    override fun onResume() {
        super.onResume()
        btnOk!!.isEnabled = verifReseau()
        Log.d(TAG, "OnResume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "OnRestart")
    }

    override fun onClick(v: View) {
        val myToast = Toast.makeText(this, "Vérifiez l'url ou les identifiants de connexion.", Toast.LENGTH_SHORT)

        when (v.id) {
            R.id.checkBox -> {
                editor!!.putBoolean("remember", checkBox!!.isChecked)
                editor!!.commit()
                if (!checkBox!!.isChecked) {
                    editor!!.putString("login", "")
                    editor!!.commit()
                }
            }
            R.id.button -> {
                if (checkBox!!.isChecked) {
                    editor!!.putString("login", etPseudo!!.text.toString())
                    editor!!.commit()
                }
                activityScope.launch {
                    try {
                        val hash : String = toDoRepository.authenticate(etPseudo!!.text.toString(), etMdp!!.text.toString())
                        editor!!.putString("hash", hash)
                        Log.d(TAG,hash)
                        editor!!.commit()
                        val bundle = Bundle()
                        bundle.putString("pseudo", etPseudo!!.text.toString())
                        val toSecondAct: Intent = Intent(this@MainActivity, ChoixListActivity::class.java)
                        toSecondAct.putExtras(bundle)
                        startActivity(toSecondAct)
                    } catch (e: Exception) {
                        myToast.show()
                        Log.d(TAG,e.toString())
                    }
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

    companion object {
        private const val TAG = "PMRMoi"
    }
}