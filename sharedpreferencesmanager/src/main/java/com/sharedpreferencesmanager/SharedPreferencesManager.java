package com.sharedpreferencesmanager;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

/**
 * Created by aslan on 5/9/2017.
 */

public class SharedPreferencesManager {

    private Context context;
    private String sharedPrefsTag = "MySharedPreferences";
    private int sharedPrefsMode = Context.MODE_PRIVATE;
    private SharedPreferences sharedPrefs;
    private SharedPreferences.Editor editor;

    public SharedPreferencesManager(Context context) {
        this.context = context;
        sharedPrefs = context.getSharedPreferences(sharedPrefsTag, sharedPrefsMode);
    }

    public <T> SharedPreferencesManager setValue(int value, T myData) {
        editor = sharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(myData);
        editor.putString("MyObject", json);
        editor.apply();
        return this;
    }

    public <T> T getValue(Class<T> data) {
        Gson gson = new Gson();
        String json = sharedPrefs.getString("MyObject", "");
        return gson.fromJson(json, data);
    }

}
