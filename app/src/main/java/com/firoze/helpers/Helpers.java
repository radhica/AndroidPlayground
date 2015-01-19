package com.firoze.helpers;

import android.os.Build;

/**
 * Created by firozerakib on 1/19/15.
 * Helpers - a bunch of static Helper methods
 */
public class Helpers {
    public static boolean isLollipop() {
        // Check if we're running on Android 5.0 or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return true;
        } else {
            return false;
        }
    }
}
