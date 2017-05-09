package com.sampleApp;

/**
 * Created by aslan on 5/10/2017.
 */

public enum SharedPrefKeys {
    TRAIN(0),
    CAR(1),
    PLANE(3),
    BICYCLE(4);

    private int intValue;

    private SharedPrefKeys(int value) {
        this.intValue = value;
    }

    public int toInt() {
        return intValue;
    }
}

