package com.example.administrator.anima_7;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.IntEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.ActivityManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.listView);
        mListView.setVisibility(View.GONE);
        btn = (Button) findViewById(R.id.btn);

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(i + 1 + "");
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        mListView.setAdapter(adapter);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //property animator
//                AnimatorSet animatorSet = new AnimatorSet();
//                animatorSet.playTogether(
//                        ObjectAnimator.ofFloat(btn, "translationX", 0,200)
////                        .addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
////                            @Override
////                            public void onAnimationUpdate(ValueAnimator animation) {
////                            }
////                        })
//                        ,
//                        ObjectAnimator.ofFloat(btn, "translationY", 0,200),
//                        ObjectAnimator.ofInt(btn, "backgroundColor", 0xffffffff, 0xff000000)
//                );
//                animatorSet.setInterpolator(new AccelerateInterpolator());
//                animatorSet.setDuration(2000);
//                animatorSet.start();
                //拉伸view但是连字也拉伸了
//                ObjectAnimator.ofFloat(btn,"scaleX",1.5f).start();
                //只是把宽度变大
                performAnimate(btn, 1.3f);

            }
        });

//        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
//        activityManager.getRunningAppProcesses();
    }

    private void performAnimate(final Button btn, float scale) {
        int start = btn.getWidth();
        final int end = (int) (start * scale);
        final int _start = start;
        //1.通过包装view的类，提供对应的属性方法实现
//        ViewWrapper viewWrapper = new ViewWrapper(btn);
//        ObjectAnimator.ofInt(viewWrapper, "width", end).setDuration(300).start();
        //2.通过ValueAnimator的变化实现
        ValueAnimator valueAnimator = ValueAnimator.ofInt(1, 100);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            private IntEvaluator mEvaluator = new IntEvaluator();

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                btn.getLayoutParams().width = mEvaluator.evaluate(fraction, _start, end);
                btn.requestLayout();
            }
        });
        valueAnimator.setDuration(1000).start();
    }

    private static class ViewWrapper {
        private View mTarget;

        public ViewWrapper(View v) {
            mTarget = v;
        }

        public int getWidth() {
            return mTarget.getLayoutParams().width;
        }

        public void setWidth(int width) {
            mTarget.getLayoutParams().width = width;
            mTarget.requestLayout();
        }
    }
}
