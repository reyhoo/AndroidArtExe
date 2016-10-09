package com.example.administrator.scrollerdemo;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.Scroller;

import com.nineoldandroids.animation.Animator;

/**
 * Created by Administrator on 2016/10/9.
 */

public class ScrollerButton extends Button {

    private Scroller mScroller;

    public ScrollerButton(Context context) {
        super(context);
        init();
    }


    public ScrollerButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScrollerButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void smoothScrollTo(int destX, int destY) {
        final int scrollX = getScrollX();
        final int scrollY = getScrollY();
        final int deltaX = destX - scrollX;
        final int deltaY = destY - scrollY;
        ValueAnimator animator = ValueAnimator.ofInt(0,1).setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                scrollTo( (int)(scrollX +deltaX*fraction), (int)(scrollY +deltaY*fraction));
            }
        });
        animator.start();
//        mScroller.startScroll(scrollX, scrollY, deltaX, deltaY, 1000);
//        invalidate();
    }

    private void init() {
        mScroller = new Scroller(getContext());
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            postInvalidate();
        }
    }
}
