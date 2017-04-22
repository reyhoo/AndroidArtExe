package com.example.yaolei.client;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/3/22.
 */

public class MyListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        tv.setText("I'm "+getClass().getSimpleName());
        setContentView(tv);
    }
}
