package com.example.administrator.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/10/9.
 */

/***
 * View对wrap_content的体现是match_parent，要想体现wrap_content必须重写onMeasure方法并且指定默认尺寸
 */
public class MyView extends View {

    private int mDefaultWidth = 100;
    private int mDefaultHeight = 100;

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if(widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(mDefaultWidth,mDefaultHeight);
        }else if(widthMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(mDefaultWidth,heightSize);
        }else if(heightMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSize,mDefaultHeight);
        }
    }
}
