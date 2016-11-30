package com.example.administrator.broadcastincludestop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent("com.test.MyReceiver.Action");
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);//对未启动过的应用或强制停止的应用发广播，其可以收到广播。
        sendBroadcast(intent);
    }
}
