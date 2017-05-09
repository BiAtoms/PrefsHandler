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
    private String sharedPrefsTag = "MySharedPreferences";
    private int sharedPrefsMode = Context.MODE_PRIVATE;
    private SharedPreferences sharedPrefs;
    private SharedPreferences.Editor editor;

    private boolean logIsEnabled = false;
    private boolean toastIsEnabled = false;
    private boolean snackbarIsEnabled = false;


    public SharedPreferencesManager(Context context) {
        this.context = context;
        sharedPrefs = context.getSharedPreferences(sharedPrefsTag, sharedPrefsMode);
    }

    public <T> SharedPreferencesManager setValue(String key, T myData) {
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
        Gson gson = new Gson();
        try {
            String json = sharedPrefs.getString(key, "");
            return gson.fromJson(json, data);
        } catch (Throwable t) {
            getFeedBack(t);
        }
        return null;
    }

    private void getFeedBack(Throwable t) {
        writeToLog(t);
        writeToSnackBar(t);
        writeToToast(t);
    }

    private void writeToLog(Throwable t) {
        if (logIsEnabled)
            Log.e(TAG, t.getMessage());
    }

    private void writeToSnackBar(Throwable t) {
        if (snackbarIsEnabled) {
            //TODO: Implement SnackBar
        }
    }

    private void writeToToast(Throwable t) {
        if (toastIsEnabled)
            Toast.makeText(context, TAG + " " + t.getMessage(), Toast.LENGTH_LONG).show();
    }

    /**
     * Default is false
     */
    public void setLogIsEnabled(boolean enable) {
        this.logIsEnabled = enable;
    }

    /**
     * Default is false
     */
    public void setToastIsEnabled(boolean enable) {
        this.toastIsEnabled = enable;
    }

    /**
     * Default is false
     */
    public void setSnackbarIsEnabled(boolean enable) {
        this.snackbarIsEnabled = enable;
    }

    /**
     * Default is false
     */
    public boolean LogIsEnabled() {
        return this.logIsEnabled;
    }

    /**
     * Default is false
     */
    public boolean ToastIsEnabled() {
        return this.toastIsEnabled;
    }

    /**
     * Default is false
     */
    public boolean SnackbarIsEnabled() {
        return this.snackbarIsEnabled;
    }


}
