package com.example.anwser_mac.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

/**
 * Created by anwser_mac on 2017/4/28.
 */

public
class
ShineTextView extends android.support.v7.widget.AppCompatTextView {

    private LinearGradient mLinearGradient;

    private Matrix mGradientMatrix;

    private Paint mPaint;

    private int mViewWidth = 0;

    private int mTranslate = 0;

    public ShineTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //组件大小改变后的回调
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mViewWidth == 0) {
            mViewWidth = getMeasuredWidth();
            Log.d("MeasureWidth", String.valueOf(getMeasuredWidth()));
            if (mViewWidth > 0) {
                mPaint = getPaint();
                //设置一个宽度渲染器
                mLinearGradient = new LinearGradient(0, 0, mViewWidth, 0, new int[] {Color.BLUE, 0xffffffff, Color.BLUE}, null, Shader.TileMode.CLAMP);
                mPaint.setShader(mLinearGradient);
                mGradientMatrix = new Matrix();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mGradientMatrix != null) {
            mTranslate += mViewWidth / 5;
            if (mTranslate > 2 * mViewWidth) {
                mTranslate = -mViewWidth;
            }
            mGradientMatrix.setTranslate(mTranslate, 0);
            mLinearGradient.setLocalMatrix(mGradientMatrix);
            postInvalidateDelayed(100);
        }
    }
}
