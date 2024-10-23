package com.abisoft.trello.model
import android.content.Context

class TokenManager(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        val editor = sharedPreferences.edit()
        editor.putString("TOKEN", token)
        editor.apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString("TOKEN", null)
    }

    fun clearToken() {
        val editor = sharedPreferences.edit()
        editor.remove("TOKEN")
        editor.apply()
    }
}
