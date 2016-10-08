package com.example.administrator.viewflipperdemo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private MyViewFlipper vfContainer;

    private GestureDetector mGestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGestureDetector = new GestureDetector(this, new MyGestureListener());
        vfContainer = (MyViewFlipper) findViewById(R.id.viewFlipper);
        vfContainer.setGestureDetector(mGestureDetector);
        vfContainer.addView(createView(1));
        vfContainer.addView(createView(2));
        vfContainer.addView(createView(3));
    }

    private View createView(int index){
//        TextView tv = new TextView(this);
//        tv.setText("tv "+ index);
        ListView lv = new ListView(this);
        List<String>list = new ArrayList<>();
        for (int i = 0 ; i < 30 ; i ++){
            list.add("lv"+index+":"+(i + 1));
        }
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        lv.setAdapter(adapter);
        return lv;
    }
    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {


        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
            if (e1.getX() - e2.getX() > 20) {
                vfContainer.setInAnimation(MainActivity.this, R.anim.slide_in_right);
                vfContainer.setOutAnimation(MainActivity.this, R.anim.slide_out_left);
                vfContainer.showNext();
            } else if (e2.getX() - e1.getX() > 20) {
                vfContainer.setInAnimation(MainActivity.this, R.anim.slide_in_left);
                vfContainer.setOutAnimation(MainActivity.this, R.anim.slide_out_right);
                vfContainer.showPrevious();
            }
            return super.onFling(e1, e2, velocityX, velocityY);
        }

    }
}
