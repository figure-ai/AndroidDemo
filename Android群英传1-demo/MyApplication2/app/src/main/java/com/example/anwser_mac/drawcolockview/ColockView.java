package com.example.anwser_mac.drawcolockview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by anwser_mac on 2017/5/4.
 */

public class ColockView extends View {

    private int mHeight, mWidth;

    public ColockView(Context context) {
        super(context);
    }

    public ColockView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ColockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //获取宽高参数
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        //画外圆
        Paint paintCircle = new Paint();
        paintCircle.setStyle(Paint.Style.STROKE);
        paintCircle.setAntiAlias(true);
        paintCircle.setStrokeWidth(10);
        canvas.drawCircle(mWidth/2, mHeight/2, mWidth/2, paintCircle);
        //画刻度
        Paint paintDegree = new Paint();
        paintCircle.setStrokeWidth(5);
        for (int i = 0; i < 12; i++) {
            //区分整点与非整点
            if (i%3 == 0) {
                paintDegree.setStrokeWidth(5);
                paintDegree.setTextSize(50);
//                canvas.drawLine(mWidth/2, mHeight/2-mWidth/2,mWidth/2,mHeight/2-mWidth/2+60,paintDegree);
//                String degree = String.valueOf(i);
//                canvas.drawText(degree, mWidth/2 - paintDegree.measureText(degree)/2,mHeight/2-mWidth/2+90, paintDegree);;
            } else {
                paintDegree.setStrokeWidth(5);
                paintDegree.setTextSize(30);
            }
            canvas.drawLine(mWidth/2, mHeight/2-mWidth/2,mWidth/2,mHeight/2-mWidth/2+60,paintDegree);
            String degree = String.valueOf(i);
            canvas.drawText(degree, mWidth/2 - paintDegree.measureText(degree)/2,mHeight/2-mWidth/2+90, paintDegree);
            canvas.rotate(30,mWidth/2,mHeight/2);
        }
        //画圆心
        Paint paintPointer = new Paint();
        paintPointer.setStrokeWidth(30);
        canvas.drawPoint(mWidth/2,mHeight/2,paintPointer);
        //画指针
        Paint paintHour = new Paint();
        paintHour.setStrokeWidth(20);
        Paint paintMinute = new Paint();
        paintMinute.setStrokeWidth(10);
        canvas.save();
        canvas.translate(mWidth/2, mHeight/2);
        canvas.drawLine(0, 0, 100, 100, paintHour);
        canvas.drawLine(0, 0, 200, 100, paintMinute);
        canvas.restore();
    }
}
