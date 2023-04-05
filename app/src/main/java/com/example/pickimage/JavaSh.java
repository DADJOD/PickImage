package com.example.pickimage;

import android.content.Context;
import android.content.SharedPreferences;

public class JavaSh {
    private final SharedPreferences prefs;
    Context context;
    public static final String PREFS_NAME = "MyPrefs";

    public JavaSh(Context context) {
        this.context = context;

        prefs = context.getSharedPreferences(PREFS_NAME, 0);
    }
    public static final String FILENAME_KEY = "FILENAME_KEY";
    public String getFilenameKey() {
        return prefs.getString(FILENAME_KEY, "");
    }
    public void setFilenameKey(String name) {
        prefs.edit().putString(FILENAME_KEY, name).apply();
    }
}
