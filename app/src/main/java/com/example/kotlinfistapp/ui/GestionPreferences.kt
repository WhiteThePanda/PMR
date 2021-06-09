@file:Suppress("DEPRECATION")

package com.example.kotlinfistapp.ui

import android.os.Bundle
import android.preference.CheckBoxPreference
import android.preference.EditTextPreference
import android.preference.Preference
import android.preference.Preference.OnPreferenceChangeListener
import android.preference.PreferenceActivity
import android.util.Log
import android.widget.Toast
import com.example.kotlinfistapp.R
import com.example.kotlinfistapp.data.source.remote.RemoteDataSource


class GestionPreferences : PreferenceActivity(), OnPreferenceChangeListener {
    var cbp: CheckBoxPreference? = null
    var edtpl: EditTextPreference? = null
    var edtURLAPI : EditTextPreference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.preferences)

        cbp = findPreference("remember") as CheckBoxPreference
        edtpl = findPreference("login") as EditTextPreference
        edtURLAPI = findPreference("urlData") as EditTextPreference

        cbp!!.onPreferenceChangeListener = this
        edtURLAPI!!.onPreferenceChangeListener = this

    }

    override fun onPreferenceChange(preference: Preference, newValue: Any): Boolean {
        when (preference) {
            edtURLAPI -> {
                try {
                    Log.i("URLAPI", newValue.toString())
                    RemoteDataSource.refreshURL(newValue.toString())
                }
                catch(e : java.lang.IllegalArgumentException){
                    Toast.makeText(this, "mauvaise url", Toast.LENGTH_SHORT).show()
                }
            }

            cbp -> {
                val t = Toast.makeText(this, "click cb :$newValue", Toast.LENGTH_SHORT)
                t.show()
                if (newValue == false) {
                    edtpl!!.text = ""
                }
            }
        }
        return true
    }
}