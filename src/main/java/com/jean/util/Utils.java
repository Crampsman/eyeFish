package com.jean.util;

/**
 * Created by stas on 18.07.15.
 */
public class Utils {
    public static float kelvinToCelsius(float kelvinTemp) {
        float c = (kelvinTemp - Constants.KELVIN_VALUE) * 1;

        return round(c);
    }

    private static float round(float number) {
        float tmp = number * 100;
        return (float) (int) ((tmp - (int) tmp) >= 0.5f ? tmp + 1 : tmp) / 100;
    }
}