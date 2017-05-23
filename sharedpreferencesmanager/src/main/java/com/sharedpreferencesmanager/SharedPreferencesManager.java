package com.sharedpreferencesmanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

/**
 * Created by aslan on 5/9/2017.
 */

public class SharedPreferencesManager {

    private static String TAG = "SharedPrefsManager";

    private Context context;
    private final String defaultSharedPrefsTag = "MySharedPreferences";
    private final int defaultSharedPrefsMode = Context.MODE_PRIVATE;

    private String sharedPrefsTag;
    private int sharedPrefsMode;

    private SharedPreferences sharedPrefs;
    private SharedPreferences.Editor editor;

    private boolean logIsEnabled = false;
    private boolean toastIsEnabled = false;


    public SharedPreferencesManager(Context context) {
        this.context = context;
        this.sharedPrefsMode = defaultSharedPrefsMode;
        this.sharedPrefsTag = defaultSharedPrefsTag;

        sharedPrefs = context.getSharedPreferences(defaultSharedPrefsTag, defaultSharedPrefsMode);
    }

    public SharedPreferencesManager(Context context, String sharedPrefsTag) {
        this.context = context;
        this.sharedPrefsTag = sharedPrefsTag;
        this.sharedPrefsMode = defaultSharedPrefsMode;

        sharedPrefs = context.getSharedPreferences(sharedPrefsTag, defaultSharedPrefsMode);
    }

    public SharedPreferencesManager(Context context, int sharedPrefsMode) {
        this.context = context;
        this.sharedPrefsMode = sharedPrefsMode;
        this.sharedPrefsTag = defaultSharedPrefsTag;

        sharedPrefs = context.getSharedPreferences(defaultSharedPrefsTag, sharedPrefsMode);
    }

    public SharedPreferencesManager(Context context, String sharedPrefsTag, int sharedPrefsMode) {
        this.context = context;
        this.sharedPrefsMode = sharedPrefsMode;
        this.sharedPrefsTag = sharedPrefsTag;

        sharedPrefs = context.getSharedPreferences(sharedPrefsTag, sharedPrefsMode);
    }

    public <T> SharedPreferencesManager setValue(String key, T myData) {
        //TODO: Check if the key exists and handle it!
        editor = sharedPrefs.edit();

        try {
            Gson gson = new Gson();
            String json = gson.toJson(myData);
            editor.putString(String.valueOf(key), json);
            editor.apply();

        } catch (Throwable t) {
            getFeedBack(t);
        }
        return this;
    }

    public <T> SharedPreferencesManager setValue(int key, T myData) {
        return this.setValue(String.valueOf(key), myData);
    }

    public <T> T getValue(int key, Class<T> data) {
        return this.getValue(String.valueOf(key), data);
    }

    public <T> T getValue(String key, Class<T> data) {
        //TODO: Add default String for not-exist case!
        Gson gson = new Gson();
        try {
            String json = sharedPrefs.getString(key, "");
            if (json.equals("")) return null;
            return gson.fromJson(json, data);
        } catch (Throwable t) {
            getFeedBack(t);
        }
        return null;
    }

    //region Logging
    private void getFeedBack(Throwable t) {
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

//#endregion

    public void clearAll() {
        editor = sharedPrefs.edit();
        editor.clear().apply();
    }

    public SharedPreferencesManager clearData(String key) {
        editor = sharedPrefs.edit();
        try{
            editor.remove(key).apply();
        }catch (Throwable t)
        {
            getFeedBack(t);
        }
        return this;
    }

    public void clearData(int key) {
        clearData(String.valueOf(key));
    }

    public String getSharedPrefsTag() {
        return this.sharedPrefsTag;
    }

    public int getSharedPrefsMode() {
        return this.sharedPrefsMode;
    }


}
