package com.example.pickimage

import android.content.Context
import android.content.SharedPreferences

class PreferencesHelper(context: Context) {
    private val prefs: SharedPreferences

    init {
        prefs = context.getSharedPreferences(PREFS_NAME, 0)
    }

    var filenameKey: String?
        get() = prefs.getString(FILENAME_KEY, "")
        set(name) {
            prefs.edit().putString(FILENAME_KEY, name).apply()
        }

    companion object {
        const val PREFS_NAME = "MyPrefs"
        const val FILENAME_KEY = "FILENAME_KEY"

        private var instance: PreferencesHelper? = null

        fun getInstance(context: Context): PreferencesHelper? {
            if (instance == null) {
                instance = PreferencesHelper(context)
            }
            return instance
        }
    }
}