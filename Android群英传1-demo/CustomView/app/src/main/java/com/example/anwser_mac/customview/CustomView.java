package com.example.anwser_mac.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anwser_mac on 2017/4/28.
 */

public class CustomView extends View {

    public CustomView(Context context) {
        super(context);
    }

    //从XML加载组件后的回调
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    //组件大小该变后的回调
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    //回调该方法用来进行测量
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    //回调该方法来确定显示的位置
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    //重写该方法来绘制view的显示内容
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
