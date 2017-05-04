package com.example.anwser_mac.dragviewtest;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Scroller;

/**
 * Created by anwser_mac on 2017/5/3.
 */

public
class
DrawView3 extends View {

    private int lastX;
    private int lastY;
    private Scroller mScroller;

    public void initView(Context context) {
        setBackgroundColor(Color.YELLOW);
        mScroller = new Scroller(context);
    }

    public DrawView3(Context context) {
        super(context);
        initView(context);
    }

    public DrawView3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public DrawView3(Context context, AttributeSet attrs) {
        super(context,attrs);
        initView(context);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        //判断Scroller是否执行完毕
        if (mScroller.computeScrollOffset()) {
            ((View) getParent()).scrollTo(
                    mScroller.getCurrX(),
                    mScroller.getCurrY());
            //通过重绘来不断调用computeScroller
            invalidate();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = (int) event.getX();
                lastY = (int) event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                ((View) getParent()).scrollBy(-offsetX, -offsetY);
                break;
            case MotionEvent.ACTION_UP:
                //手指离开时，执行滑动过程
                View viewGroup = ((View) getParent());
                mScroller.startScroll(
                        viewGroup.getScrollX(),
                        viewGroup.getScrollY(),
                        -viewGroup.getScrollX(),
                        -viewGroup.getScrollY(),
                        3000);
                invalidate();
                break;
        }
        return true;
    }
}
