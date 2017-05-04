package com.example.anwser_mac.dragviewtest;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import static android.R.attr.offset;
import static android.R.attr.x;
import static android.R.attr.y;

/**
 * Created by anwser_mac on 2017/5/3.
 */

public
class
DrawView1 extends View {

    private int lastX;
    private int lastY;

    private void intiView() {
        //给view设置背景颜色，便于观察
        setBackgroundColor(Color.YELLOW);
    }

    public DrawView1(Context context) {
        super(context);
        intiView();
    }

    public DrawView1(Context context, AttributeSet attrs) {
        super(context, attrs);
        intiView();
    }

    public DrawView1(Context context, AttributeSet attrs, int defStyleAttr) {
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
                //调用layout方法在当前的left、top、right、bottom的基础上那个加上偏移量；
                layout(getLeft() + offsetx,
                        getTop() + offsety,
                        getRight() + offsetx,
                        getBottom() + offsety);
//                //同时对left和right进行偏移
//                offsetLeftAndRight(offsetx);
//                //同时对top和bottom进行偏移
//                offsetTopAndBottom(offsety);
                Log.d("Lch", "ACTION_MOVE");
                //使用绝对坐标系时需重新设置初始坐标
//                lastX = x;
//                lastY = y;
                break;
        }
        return true;
    }
}
