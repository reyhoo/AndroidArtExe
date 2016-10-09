package com.example.administrator.scrollerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ScrollerButton smoothScrollBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        smoothScrollBtn = (ScrollerButton) findViewById(R.id.smoothScrollBtn);
        smoothScrollBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.smoothScrollBtn:
                smoothScrollBtn.smoothScrollTo(200,0);
                break;
        }
    }
}
