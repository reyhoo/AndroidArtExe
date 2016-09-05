package com.example.aidlservice;

import android.app.Application;
import android.content.Intent;

/**
 * Created by yaolei on 2016/8/21.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        startService(new Intent(this,BookManagerService.class));
    }
}
