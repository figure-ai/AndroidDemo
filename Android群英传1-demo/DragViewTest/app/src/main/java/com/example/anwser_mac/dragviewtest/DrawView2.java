package com.example.anwser_mac.dragviewtest;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by anwser_mac on 2017/5/3.
 */

public class DrawView2 extends View {
    private int lastX;
    private int lastY;

    private void intiView() {
        //给view设置背景颜色，便于观察
        setBackgroundColor(Color.YELLOW);
    }

    public DrawView2(Context context) {
        super(context);
        intiView();
    }

    public DrawView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        intiView();
    }

    public DrawView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        intiView();
    }

    //视图坐标方式

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
//        int x = (int) (event.getRawX());
//        int y = (int) (event.getRawY());
        Log.d("Lch", "onTouchEvent x: "+x+"y: "+y);
        Log.d("Lch", "onTouchEvent RawX: " + event.getRawX() + " RawY: " + event.getRawY());

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //记录触摸点的坐标
                lastX = x;
                lastY = y;
                Log.d("Lch", "ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                //计算偏移量
                int offsetx = x - lastX;
                int offsety = y - lastY;
                ///LayoutParams方法
//                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)getLayoutParams();
//                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
//                layoutParams.leftMargin = getLeft() + offsetx;
//                layoutParams.topMargin = getTop() + offsety;
//                setLayoutParams(layoutParams);
                ///scrollBy,scrollTo 方法
                ((View)getParent()).scrollBy(-offsetx, -offsety);
                break;
        }
        return true;
    }
}
