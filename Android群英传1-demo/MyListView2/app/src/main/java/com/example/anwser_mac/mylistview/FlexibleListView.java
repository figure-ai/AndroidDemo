package com.example.anwser_mac.mylistview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.ListView;

import org.w3c.dom.Attr;

/**
 * Created by anwser_mac on 2017/5/3.
 */

public
class
FlexibleListView extends ListView {

    private static int mMaxOverDistance = 50;
    private Context mContext;

    public FlexibleListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }

    public FlexibleListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    public FlexibleListView(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    private void initView() {
        DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        float density = metrics.density;
        mMaxOverDistance = (int) (density * mMaxOverDistance);
    }

    //控制滑到边缘到处理方法
    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, mMaxOverDistance, isTouchEvent);
    }
}
