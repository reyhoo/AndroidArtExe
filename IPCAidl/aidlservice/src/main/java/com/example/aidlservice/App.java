package com.example.aidlservice;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

/**
 * Created by yaolei on 2016/8/21.
 */
public class App extends Application {
    public static Context instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
//        startService(new Intent(this,BookManagerService.class));
    }
}
