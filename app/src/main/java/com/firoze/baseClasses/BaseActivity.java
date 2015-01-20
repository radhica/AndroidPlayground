package com.firoze.baseClasses;

import android.app.Activity;
import android.util.Log;

/**
 * Created by firozerakib on 1/19/15.
 * BaseActivity - boilerplate Activity
 */
public abstract class BaseActivity extends Activity {
    private int version_API;

    public BaseActivity() {
        version_API = android.os.Build.VERSION.SDK_INT;
    }

    public abstract String getTag();

    protected void log(String msg) {
        Log.v(getTag(), msg);
    }
}
