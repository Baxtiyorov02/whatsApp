package com.example.whatsapp.data.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit


class AuthPrefs(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)

    fun setLoggedIn(isLoggedIn: Boolean) {
        prefs.edit { putBoolean("is_logged_in", isLoggedIn) }
    }

    fun isLoggedIn(): Boolean = prefs.getBoolean("is_logged_in", false)

    fun clear() {
        prefs.edit { clear() }
    }
}
