@file:Suppress("DEPRECATION")

package com.example.kotlinfistapp

import android.content.Intent
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
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


class MainActivity : AppCompatActivity(), View.OnClickListener {
    var sp: SharedPreferences? = null
    var editor: Editor? = null
    var btnOk: Button? = null
    var checkBox: CheckBox? = null
    var etPseudo: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sp = PreferenceManager.getDefaultSharedPreferences(this)
        editor = sp?.edit()
        btnOk = findViewById(R.id.button)
        checkBox = findViewById(R.id.checkBox)
        etPseudo = findViewById(R.id.pseudo)
        btnOk?.setOnClickListener(this)
        findViewById<View>(R.id.pseudo).setOnClickListener(this)
        checkBox?.setOnClickListener(this)
        Log.d(TAG, "OnCreate")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.menu_settings) {
            val myToast: Toast
            myToast = Toast.makeText(this, "preference", Toast.LENGTH_LONG)
            myToast.show()
            val toPrefAct: Intent
            toPrefAct = Intent(this@MainActivity, GestionPreferences::class.java)
            startActivity(toPrefAct)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "OnStart")
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
        Log.d(TAG, "OnResume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "OnRestart")
    }

    override fun onClick(v: View) {
        val myToast: Toast
        when (v.id) {
            R.id.checkBox -> {
                myToast = Toast.makeText(this, "CheckBox", Toast.LENGTH_LONG)
                myToast.show()
                editor!!.putBoolean("remember", checkBox!!.isChecked)
                Log.d(TAG, "" + checkBox!!.isChecked)
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
                val bundle = Bundle()
                bundle.putString("pseudo", etPseudo!!.text.toString())
                val toSecondAct: Intent
                toSecondAct = Intent(this@MainActivity, ChoixListActivity::class.java)
                toSecondAct.putExtras(bundle)
                startActivity(toSecondAct)
            }
            R.id.pseudo -> {
                myToast = Toast.makeText(this, "pseudo", Toast.LENGTH_LONG)
                myToast.show()
            }
        }
    }

    companion object {
        private const val TAG = "PMRMoi"
    }
}