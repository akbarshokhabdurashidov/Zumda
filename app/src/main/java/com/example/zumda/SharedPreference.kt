package com.example.zumda

import android.content.Context
import android.content.SharedPreferences

class SharedPreference (context: Context){

    private var preferences: SharedPreferences =
        context.getSharedPreferences("APP_PREFS_NAME", Context.MODE_PRIVATE)
    private lateinit var editor: SharedPreferences.Editor


    fun setToken(token: String) {
        editor.putString("token", token)
        editor.commit()
    }

    fun getToken(): String {
        return preferences.getString("token", "") ?: ""
    }


    fun setHasToken(hasToken: Boolean) {
        editor = preferences.edit()
        editor.putBoolean("hasToken", hasToken)
        editor.apply()
    }

    fun getHasToken(): Boolean {
        return preferences.getBoolean("hasToken", false)
    }



}