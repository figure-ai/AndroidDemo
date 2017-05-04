package com.example.anwser_mac.sufaceviewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by anwser_mac on 2017/5/4.
 */

public
class
SinView
        extends SurfaceView implements SurfaceHolder.Callback, Runnable{

    private SurfaceHolder mHolder;
    private Canvas mCanvas;
    private boolean mIsDrawing;
    private int x = 0;
    private int y = 0;
    private Path mPath;
    private Paint mPaint;

    public SinView(Context context) {
        super(context);
        initView();
    }

    public SinView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView();
    }

    public SinView(Context context, AttributeSet attrs, int defStyleRes) {
        super(context, attrs, defStyleRes);
        initView();
    }


    private void initView() {
        mHolder = getHolder();
        mHolder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);
        mPath = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.YELLOW);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.ROUND);

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mIsDrawing = true;
        mPath.moveTo(0, 800);
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mIsDrawing = false;
    }

    @Override
    public void run() {
        while (mIsDrawing) {
            draw();
            x +=1;
            y = (int) (300*Math.sin(x*2*Math.PI/180) + 800);
            mPath.lineTo(x, y);
        }
    }

    private void draw() {
        mCanvas = mHolder.lockCanvas();
        //SurfaceView背景
        mCanvas.drawColor(Color.BLACK);
        mCanvas.drawPath(mPath, mPaint);
        mHolder.unlockCanvasAndPost(mCanvas);
    }
}
