package com.example.anwser_mac.drawcolockview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by anwser_mac on 2017/5/4.
 */

public class LayerView extends View {

    public LayerView(Context context) {
        super(context);
    }
    public LayerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public LayerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        Paint mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        canvas.drawCircle(150, 150, 100, mPaint);
        //当透明度为127：即半透明
        //255：完全不透明
        //0: 完全透明
        //layer入栈，之后的操作在此图层上
        canvas.saveLayerAlpha(0, 0, 400, 400, 127, Canvas.CLIP_TO_LAYER_SAVE_FLAG);
        mPaint.setColor(Color.RED);
        canvas.drawCircle(200, 200, 100, mPaint);
        canvas.restore();
    }
}

