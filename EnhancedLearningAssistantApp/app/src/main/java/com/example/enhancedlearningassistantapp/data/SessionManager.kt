package com.example.enhancedlearningassistantapp.data

import android.content.Context

class SessionManager(context: Context) {
    private val prefs = context.getSharedPreferences("learning_assistant_prefs", Context.MODE_PRIVATE)

    fun saveUsername(username: String) {
        prefs.edit().putString("username", username).apply()
    }

    fun getUsername(): String = prefs.getString("username", "Student") ?: "Student"

    fun saveInterests(interests: Set<String>) {
        prefs.edit().putStringSet("interests", interests).apply()
    }

    fun getInterests(): Set<String> = prefs.getStringSet("interests", emptySet()) ?: emptySet()
}
