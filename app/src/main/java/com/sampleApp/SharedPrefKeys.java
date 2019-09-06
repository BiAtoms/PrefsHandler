package com.sampleApp;

import androidx.annotation.NonNull;

import java.util.ArrayList;

/**
 * Created by aslan on 5/10/2017.
 */

enum SharedPrefKeys {
    TRAIN("Train"),
    CAR("Car"),
    LIST("List"),
    PLANE("Plane"),
    BICYCLE("Bicycle");

    private String stringValue;

    private SharedPrefKeys(String name) {
        this.stringValue = name;
    }

    @Override
    @NonNull
    public String toString() {
        return stringValue;
    }

    public static ArrayList<String> getKeysAsStringArrayList() {
        ArrayList<String> keys = new ArrayList<>();

        for (SharedPrefKeys key : values()) {
            keys.add(key.toString());
        }
        return keys;
    }
}

