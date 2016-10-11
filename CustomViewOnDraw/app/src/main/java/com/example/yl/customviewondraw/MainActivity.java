package com.example.yl.customviewondraw;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private ImageView iv;
    private Button btn;


    private int currDegress;

    private Runnable rotateTask = new Runnable() {
        @Override
        public void run() {
            if(currDegress == 360){
                currDegress = 0;
            }
            currDegress ++;
            iv.setRotation(currDegress);
            iv.setPivotX(0);
            iv.setPivotY(0);
            handler.postDelayed(rotateTask,20);
        }
    };

    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.iv);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacks(rotateTask);
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        btn.setClickable(false);
        handler.post(rotateTask);
    }
}
