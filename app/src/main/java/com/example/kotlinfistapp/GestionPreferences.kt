@file:Suppress("DEPRECATION")

package com.example.kotlinfistapp

import android.os.Bundle
import android.preference.CheckBoxPreference
import android.preference.EditTextPreference
import android.preference.Preference
import android.preference.Preference.OnPreferenceChangeListener
import android.preference.PreferenceActivity
import android.widget.Toast


class GestionPreferences : PreferenceActivity(), OnPreferenceChangeListener {
    var cbp: CheckBoxPreference? = null
    var edtpl: EditTextPreference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.preferences)
        cbp = findPreference("remember") as CheckBoxPreference
        edtpl = findPreference("login") as EditTextPreference
        cbp!!.onPreferenceChangeListener = this
    }

    override fun onPreferenceChange(preference: Preference, newValue: Any): Boolean {

        val t = Toast.makeText(this, "click cb :$newValue", Toast.LENGTH_SHORT)
        t.show()
        if (newValue == false) {
            edtpl!!.text = ""
        }
        return true
    }
}