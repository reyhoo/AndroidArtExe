package com.example.administrator.viewflipperdemo;

import android.app.Application;
import android.os.Process;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/12/21.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        if(0 == 0){
//            Toast.makeText(this, "非法应用", Toast.LENGTH_SHORT).show();
//           System.exit(1);
        }
    }
}
