package com.sampleApp;

/**
 * Created by aslan on 5/10/2017.
 */

public enum SharedPrefKeys {
    TRAIN(0, "Train"),
    CAR(1, "Car"),
    PLANE(3, "Plane"),
    BICYCLE(4, "Bicycle");

    private int intValue;
    private String stringValue;

    private SharedPrefKeys(int value, String name) {
        this.intValue = value;
        this.stringValue = name;
    }

    public int toInt() {
        return intValue;
    }

    @Override
    public String toString() {
        return stringValue;
    }

}

