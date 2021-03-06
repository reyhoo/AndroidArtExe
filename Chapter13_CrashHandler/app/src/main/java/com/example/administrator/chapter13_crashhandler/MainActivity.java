package com.example.administrator.chapter13_crashhandler;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity_";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getClass().getCanonicalName());
        findViewById(R.id.btn).setOnClickListener(this);
        Log.i(TAG,"onCreate");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn:
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(getApplicationContext(),SecondActivity.class));
                startActivity(intent);
                break;
        }
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG,"onRestart");
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"onStart");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"onResume");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG,"onSaveInstanceState:"+(outState!=null));
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"onDestroy");
    }
}
