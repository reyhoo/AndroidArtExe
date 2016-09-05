package com.example.binderpool.client;

import android.app.Application;

import com.example.binderpoolcommon.BinderPool;

/**
 * Created by yaolei on 2016/8/23.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        new Thread() {
            @Override
            public void run() {
                BinderPool.getInstance(App.this);
            }
        }.start();

    }
}
