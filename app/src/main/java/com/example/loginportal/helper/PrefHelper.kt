package com.example.loginportal.helper

import android.content.Context
import android.content.SharedPreferences

class PrefHelper(context: Context){
    private val PREF_NAME = "pref"
    private var sharedPref : SharedPreferences
    val editor : SharedPreferences.Editor

    init {
        sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        editor = sharedPref.edit()
    }

    fun put(key : String, value: String){
        editor.putString(key, value)
                .apply()
    }

    fun put(key: String, value: Boolean) {
        editor.putBoolean(key, value)
                .apply()
    }

    fun getBoolean(key: String): Boolean {
        return sharedPref.getBoolean(key, false)
    }

    fun getString(key : String) : String? {
        return sharedPref.getString(key, null)
    }

    fun clear(){
        editor.clear()
                .apply()
    }
}