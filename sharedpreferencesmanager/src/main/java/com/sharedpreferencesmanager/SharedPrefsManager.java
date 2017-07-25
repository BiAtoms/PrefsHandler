package com.sharedpreferencesmanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by aslan on 5/9/2017.
 */

public class SharedPrefsManager {

    private final static String TAG = "SharedPrefsManager";

    private Context context;
    private final String defaultSharedPrefsTag = "MySharedPreferences";
    private final int defaultSharedPrefsMode = Context.MODE_PRIVATE;

    private String sharedPrefsTag;
    private int sharedPrefsMode;

    private SharedPreferences sharedPrefs;
    private SharedPreferences.Editor editor;

    private boolean logIsEnabled = false;
    private boolean toastIsEnabled = false;

    private static SharedPrefsManager prefsManager;

    //region Getters & Setters
    public String getSharedPrefsTag() {
        return sharedPrefsTag;
    }

    public int getSharedPrefsMode() {
        return sharedPrefsMode;
    }

    public static SharedPrefsManager getInstance() {
        return prefsManager;
    }

    //endregion

    //region Constructors

    private SharedPrefsManager(Builder builder) {
        this.context = builder.context;
        this.sharedPrefsMode = builder.sharedPrefsMode;
        this.sharedPrefsTag = builder.sharedPrefsTag;

        sharedPrefs = context.getSharedPreferences(sharedPrefsTag, sharedPrefsMode);
    }

    //endregion

    //region Logging

    private void getFeedback(Throwable t) {
        writeToLog(t);
        writeToToast(t);
    }

    private void writeToLog(Throwable t) {
        if (logIsEnabled)
            Log.e(TAG, t.getMessage());
    }

    private void writeToToast(Throwable t) {
        if (toastIsEnabled)
            Toast.makeText(context, TAG + " " + t.getMessage(), Toast.LENGTH_LONG).show();
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
    public void setToastEnabled(boolean enable) {
        this.toastIsEnabled = enable;
    }


    /**
     * Default is false
     */
    public boolean LogEnabled() {
        return this.logIsEnabled;
    }

    /**
     * Default is false
     */
    public boolean ToastEnabled() {
        return this.toastIsEnabled;
    }

    //endregion

    //region Main Functionality Methods

    public static <T> SharedPrefsManager setValue(String key, T myData) {
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

    public static <T> T getValue(String key, Class<T> data) {
        //TODO: Add default String for not-exist case!
        Gson gson = new Gson();
        try {
            String json = prefsManager.sharedPrefs.getString(key, "");

            //Todo: There can be a case where user might want to save
            //Todo: "" data in SharePrefs. Take into account that!
            if (json.equals("")) return null;

            return gson.fromJson(json, data);
        } catch (Throwable t) {
            prefsManager.getFeedback(t);
        }
        return null;
    }

    public static SharedPrefsManager setValue(String key, ArrayList<String> arrayList) {
        prefsManager.editor = prefsManager.sharedPrefs.edit();

        Set<String> set = new HashSet<>();
        set.addAll(arrayList);
        prefsManager.editor.putStringSet(key, set);
        prefsManager.editor.apply();

        return prefsManager;
    }

    public static void clearAll() {
        prefsManager.editor = prefsManager.sharedPrefs.edit();
        prefsManager.editor.clear().apply();
    }

    public static SharedPrefsManager clearData(String key) {
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

        public SharedPrefsManager build() {
            return new SharedPrefsManager(this);
        }
    }
}
