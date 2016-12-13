package com.example.administrator.chapter13_crashhandler;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2016/12/13.
 */

public class TestApp extends Application {
    private static Context sInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        CrashHandler.getInstance().init(this);
    }

    public static Context getInstance() {
        return sInstance;
    }
}
