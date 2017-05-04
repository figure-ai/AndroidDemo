package com.example.anwser_mac.displayutil;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

/**
 * Created by anwser_mac on 2017/5/4.
 */

public class DrawViewTest extends View {

    private float touchX = -100, touchY = -100;

    private Paint drawPaint;

    public DrawViewTest(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public DrawViewTest(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public DrawViewTest(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        setBackgroundColor(Color.YELLOW);
        //设置画笔样式
        drawPaint = new Paint();
        drawPaint.setColor(Color.BLUE);
        drawPaint.setStrokeWidth(5);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setTextSize(100);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        touchX = event.getX();
        touchY = event.getY();
        //重绘视图
        invalidate();
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制点
//        canvas.drawPoint(touchX, touchY, drawPaint);
        //绘制直线
//       canvas.drawLine(touchX, touchY, touchX, touchY + 100, drawPaint);
        //绘制圆角矩形
//        canvas.drawRoundRect(touchX,touchY,touchX+100,touchY+100,10,45,drawPaint);
        //绘制矩形
//        canvas.drawRect(touchX, touchY, touchX + 100, touchY + 100, drawPaint);
        //绘制圆
//        canvas.drawCircle(touchX, touchY, 50, drawPaint);
        //绘制弧形扇形，倒数第二个参数为true时绘制的为扇形，否则为弧形
//        canvas.drawArc(touchX, touchY, touchX + 100, touchY + 100, 180, 90, false, drawPaint);
        //绘制椭圆形
//        canvas.drawOval(touchX, touchY, touchX + 200, touchY + 100, drawPaint);
        //绘制文本
//        canvas.drawText("我是绘制的文本", touchX, touchY, drawPaint);
        //绘制路径
//        Path path = new Path();
//        path.moveTo(touchX, touchY);
//        //lineTo()传入的参数时结束点的坐标。
//        path.lineTo(touchX + 100, touchY);
//        path.lineTo(touchX + 100, touchY + 100);
//        path.lineTo(touchX + 50, touchY + 50);
//        canvas.drawPath(path, drawPaint);
    }
}
