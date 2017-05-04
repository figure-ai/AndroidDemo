package com.example.anwser_mac.sufaceviewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by anwser_mac on 2017/5/4.
 */

public
class
SimpleDraw extends SurfaceView implements SurfaceHolder.Callback, Runnable{

    private Canvas mCanvas;
    private SurfaceHolder mHolder;
    private boolean mIsDrawing;
    private Path mPath;
    private Paint mPaint;
    public SimpleDraw(Context context){
        super(context);
        initView();
    }
    public SimpleDraw(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView();
    }
    public SimpleDraw(Context context, AttributeSet attributeSet, int defStyleAttr) {
        super(context, attributeSet, defStyleAttr);
        initView();
    }
    private void initView() {
        //初始化holder
        mHolder = getHolder();
        //设置holder的回调
        mHolder.addCallback(this);
        setFocusableInTouchMode(true);
        setFocusable(true);
        this.setKeepScreenOn(true);
        mPath = new Path();
        mPaint = new Paint();
        mPaint.setColor(Color.YELLOW);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(40);
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //开启线程绘图
        mIsDrawing = true;
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
        long start = System.currentTimeMillis();
        while (mIsDrawing) {
            draw();
        }
//        long end = System.currentTimeMillis();
//        if (end - start < 100) {
//            try {
//                Thread.sleep(100 - (end - start));
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }
    private void draw() {
        mCanvas = mHolder.lockCanvas();
        mCanvas.drawColor(Color.BLACK);
        mCanvas.drawPath(mPath, mPaint);
        mHolder.unlockCanvasAndPost(mCanvas);
//        try {
//            mCanvas = mHolder.lockCanvas();
//            mCanvas.drawColor(Color.WHITE);
//            mCanvas.drawPath(mPath, mPaint);
//        } catch (Exception e) {
//        } finally {
//            if (mCanvas != null)
//                mHolder.unlockCanvasAndPost(mCanvas);
//        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(x, y);
                break;
        }
        return true;
    }
}
