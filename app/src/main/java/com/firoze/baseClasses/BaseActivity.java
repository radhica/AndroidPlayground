package com.firoze.baseClasses;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ViewGroup;

import com.firoze.androidplayground.R;

/**
 * Created by firozerakib on 1/19/15.
 * BaseActivity - boilerplate Activity
 */
public abstract class BaseActivity extends ActionBarActivity {
    protected int version_API;

    public BaseActivity() {
        version_API = android.os.Build.VERSION.SDK_INT;
    }

    public abstract String getTag();

    protected abstract int getLayoutResource();

    protected void log(String msg) {
        Log.v(getTag(), msg);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            log("using Toolbar");
        } else {
            log("no Toolbar found, did you <include /> it in your layout?");
        }
    }
}
