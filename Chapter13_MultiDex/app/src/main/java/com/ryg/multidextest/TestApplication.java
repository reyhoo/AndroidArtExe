package com.ryg.multidextest;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.ryg.multidextest.ui.View1;

/**
 * Created by renyugang on 15-5-27.
 */
public class TestApplication extends Application {

    private View1 view1 = new View1();
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
