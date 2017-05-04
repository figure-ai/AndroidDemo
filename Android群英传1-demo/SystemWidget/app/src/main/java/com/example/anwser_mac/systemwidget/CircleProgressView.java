package com.example.anwser_mac.systemwidget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.icu.util.Measure;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.RatingCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by anwser_mac on 2017/5/2.
 */

public class CircleProgressView extends View {

    private int mMeasureHeight;
    private int mMeasureWidth;

    private Paint mCirclePaint;
    private float mCircleXY;
    private float mRadius;

    private Paint mArcPaint;
    private RectF mArcRectF;
    private float mSweepAngle;
    private float mSweepValue;

    private Paint mTextPaint;
    private String mShowText;
    private float mShowTextSize;

    public CircleProgressView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CircleProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleProgressView(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mMeasureWidth = MeasureSpec.getSize(widthMeasureSpec);
        mMeasureHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(mMeasureWidth, mMeasureHeight);
        initView();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制圆，参数说明
        //x坐标，y坐标，半径，画笔
        canvas.drawCircle(mCircleXY, mCircleXY, mRadius, mCirclePaint);
        //绘制弧线
        //构成椭圆的矩形，起始角度，扫掠角度，是否封闭，画笔
        canvas.drawArc(mArcRectF, 270, mSweepAngle, false, mArcPaint);
        //绘制文字
        //要绘制的文本，起始字符的索引，结束字符索引，文本的x坐标，文本的y坐标，画笔
        canvas.drawText(mShowText, 0, mShowText.length(), mCircleXY, mCircleXY + (mShowTextSize/4), mTextPaint);

    }

    private void initView() {
        float length = 0;
        if (mMeasureHeight >= mMeasureWidth) {
            length = mMeasureWidth;
        } else {
            length = mMeasureHeight;
        }
        //计算弧的相关属性
        mCircleXY = length/2;
        mRadius = (float) (length * 0.5 /2);
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(getResources().getColor(android.R.color.holo_blue_bright));
        //计算圆的相关属性
        mArcRectF = new RectF(
                (float) (length * 0.1),
                (float) (length * 0.1),
                (float) (length * 0.9),
                (float) (length * 0.9));
        mSweepAngle = (mSweepValue / 100f) * 360f;
        mArcPaint = new Paint();
        mArcPaint.setAntiAlias(true);
        mArcPaint.setColor(getResources().getColor(android.R.color.holo_blue_bright));
        mArcPaint.setStrokeWidth((float) (length * 0.1));
        mArcPaint.setStyle(Paint.Style.STROKE);
        //计算文字的相关属性
        mShowText = setShowText();
        mShowTextSize = setShowTextSize();
        mTextPaint = new Paint();
        mTextPaint.setTextSize(mShowTextSize);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    private float setShowTextSize() {
        this.invalidate();
        return 50;
    }

    private String setShowText() {
        this.invalidate();
        return "Android Skill";
    }

    private void forceInvalidate() {
        this.invalidate();
    }

    public void setmSweepValue(float sweepValue) {
        if (sweepValue != 0) {
            mSweepValue = sweepValue;
        } else {
            mSweepAngle = 25;
        }
        this.invalidate();
    }
}
