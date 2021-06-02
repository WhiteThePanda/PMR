@file:Suppress("DEPRECATION")

package com.example.kotlinfistapp

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.AsyncTask
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast


class Reseau : AppCompatActivity(), View.OnClickListener {
    var btnR: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reseau_layout)
        btnR = findViewById(R.id.btnRequete)
        btnR?.setOnClickListener(this)
    }

    override fun onResume() {
        super.onResume()
        // TODO : le déclencher même après
        //  un simple changement en mode avion

        // S'il n'y a pas de reseau, on désactive le bouton
        btnR!!.isEnabled = verifReseau()
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

    override fun onClick(v: View) {


    }


}