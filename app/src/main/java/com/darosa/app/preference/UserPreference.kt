package com.darosa.app.preference

import android.content.Context

internal class UserPreference (context: Context) {

    companion object {
        private const val PREFS_NAME = "user_pref"
        private const val USER_ID = "user_id"
    }

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setUserId(id: String) {
        val editor = preferences.edit()
        editor.putString(USER_ID, id)
        editor.apply()
    }

    fun getUserId(): String {
        val userId = preferences.getString(USER_ID, "")
        return userId.toString()
    }

}