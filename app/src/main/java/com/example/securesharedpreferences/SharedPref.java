package com.example.securesharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    private final SharedPreferences sharedPreferences;

    public SharedPref(Context context, String preferencesName) {
        sharedPreferences = context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE);
    }

    public void put(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String get(String key) {
        return sharedPreferences.getString(key, "");
    }


}
