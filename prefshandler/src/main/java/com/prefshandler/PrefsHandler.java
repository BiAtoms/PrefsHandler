package com.prefshandler;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by aslan on 5/9/2017.
 */

public class PrefsHandler {

    private final static String TAG = "PrefsHandler";

    private String sharedPrefsTag;
    private int sharedPrefsMode;

    private SharedPreferences sharedPrefs;
    private SharedPreferences.Editor editor;

    private boolean logIsEnabled = false;

    private static PrefsHandler prefsManager;

    //region Getters & Setters
    public String getSharedPrefsTag() {
        return sharedPrefsTag;
    }

    public int getSharedPrefsMode() {
        return sharedPrefsMode;
    }

    public static PrefsHandler getInstance() {
        return prefsManager;
    }

    //endregion

    //region Constructors

    private PrefsHandler(Builder builder) {
        this.sharedPrefsMode = builder.sharedPrefsMode;
        this.sharedPrefsTag = builder.sharedPrefsTag;
        sharedPrefs = builder.context.getSharedPreferences(sharedPrefsTag, sharedPrefsMode);
        prefsManager = this;
    }

    //endregion

    //region Logging

    private void getFeedback(Throwable t) {
        writeToLog(t);
    }

    private void writeToLog(Throwable t) {
        if (logIsEnabled)
            Log.e(TAG, t.getMessage());
    }

    /**
     * Default is false
     */
    public void setLogEnabled(boolean enable) {
        this.logIsEnabled = enable;
    }


    /**
     * Default is false
     */
    public boolean LogEnabled() {
        return this.logIsEnabled;
    }


    //endregion

    //region Main Functionality Methods

    public static <T> PrefsHandler setValue(String key, T myData) {
        //TODO: Check whether the key exists and handle it!
        prefsManager.editor = prefsManager.sharedPrefs.edit();

        try {
            Gson gson = new Gson();
            String json = gson.toJson(myData);
            prefsManager.editor.putString(key, json);
            prefsManager.editor.apply();
        } catch (Throwable t) {
            prefsManager.getFeedback(t);
        }
        return prefsManager;
    }

    public static <T> T getValue(String key, Class<T> data, String _default) {
        //TODO: Add default String for not-exist case!
        Gson gson = new Gson();
        try {
            String json = prefsManager.sharedPrefs.getString(key, _default);
            if (json == null) return null;
            return gson.fromJson(json, data);
        } catch (Throwable t) {
            prefsManager.getFeedback(t);
        }
        return null;
    }

    public static <T> T getValue(String key, Class<T> data) {
        return getValue(key, data, null);
    }

    public static PrefsHandler setListValue(String key, List<String> list) {
        prefsManager.editor = prefsManager.sharedPrefs.edit();
        Set<String> set = new HashSet<>();
        set.addAll(list);
        prefsManager.editor.putStringSet(key, set);
        prefsManager.editor.apply();
        return prefsManager;
    }

    public static List<String> getListValue(String key) {
        Set<String> someStringSet = prefsManager.sharedPrefs.getStringSet(key, new HashSet<String>());
        List<String> list = new ArrayList<>();
        list.addAll(someStringSet);
        return list;
    }

    public static PrefsHandler setArrayValue(String key, String[] array) {
        prefsManager.editor = prefsManager.sharedPrefs.edit();
        Set<String> set = new HashSet<>();
        set.addAll(Arrays.asList(array));
        prefsManager.editor.putStringSet(key, set);
        prefsManager.editor.apply();
        return prefsManager;
    }

    public static String[] getArrayValue(String key) {
        Set<String> someStringSet = prefsManager.sharedPrefs.getStringSet(key, new HashSet<String>());
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.addAll(someStringSet);
        return (String[]) arrayList.toArray();
    }

    public static void clearAll() {
        prefsManager.editor = prefsManager.sharedPrefs.edit();
        prefsManager.editor.clear().apply();
    }

    public static PrefsHandler clearData(String key) {
        prefsManager.editor = prefsManager.sharedPrefs.edit();
        try {
            prefsManager.editor.remove(key).apply();
        } catch (Throwable t) {
            prefsManager.getFeedback(t);
        }
        return prefsManager;
    }
    //endregion

    public static class Builder {
        private Context context;
        private String sharedPrefsTag = "MySharedPreferences";
        private int sharedPrefsMode = Context.MODE_PRIVATE;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setSharedPrefsTag(String sharedPrefsTag) {
            this.sharedPrefsTag = sharedPrefsTag;
            return this;
        }

        public Builder setSharedPrefsMode(int sharedPrefsMode) {
            this.sharedPrefsMode = sharedPrefsMode;
            return this;
        }

        public PrefsHandler build() {
            return new PrefsHandler(this);
        }
    }
}
