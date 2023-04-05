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
    }
}

//class JavaSh(var context: Context) {
//    private val prefs: SharedPreferences
//
//    init {
//        prefs = context.getSharedPreferences(PREFS_NAME, 0)
//    }
//
//    var filenameKey: String?
//        get() = prefs.getString(FILENAME_KEY, "")
//        set(name) {
//            prefs.edit().putString(FILENAME_KEY, name).apply()
//        }
//
//    companion object {
//        const val PREFS_NAME = "MyPrefs"
//        const val FILENAME_KEY = "FILENAME_KEY"
//    }
//}

//class PreferencesHelper(context: Context) {
//    private val prefs: SharedPreferences
//
//    init {
//        prefs = context.getSharedPreferences(PREFS_NAME, 0)
//    }
//
//    companion object {
//        const val PREFS_NAME = "MyPrefs"
//    }
//}

//public class JavaSh {
//    private final SharedPreferences prefs;
//    Context context;
//
//    public static final String PREFS_NAME = "MyPrefs";
//
//    public JavaSh(Context context) {
//        this.context = context;
//
//        prefs = context.getSharedPreferences(PREFS_NAME, 0);
//    }
//}