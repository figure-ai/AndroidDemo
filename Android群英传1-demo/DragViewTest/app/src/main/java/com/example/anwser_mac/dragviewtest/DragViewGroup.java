package com.example.anwser_mac.dragviewtest;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

/**
 * Created by anwser_mac on 2017/5/3.
 */

public
class
DragViewGroup extends FrameLayout{

    private ViewDragHelper mViewDragHelper;
    private View mMenuview, mMainView;
    private int mWidth;

    public DragViewGroup(Context context) {
        super(context);
        initView();
    }
    public DragViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }
    public DragViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }
    private void initView() {
        //初始化ViewDragHelper，第一个参数为要监听的view， 第二个参数是一个callback回调
        //这个回调是整个ViewDragHelper的逻辑核心
        mViewDragHelper = ViewDragHelper.create(this, callback);
    }
    //从xml加载组件后的回调
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mMenuview = getChildAt(0);
        mMainView = getChildAt(1);
    }
    //组件大小改变后的回调
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = mMenuview.getMeasuredWidth();
    }
    //重写事件拦截方法
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return mViewDragHelper.shouldInterceptTouchEvent(ev);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //将触摸事件传递给ViewDragViewHelper，此操作必不可少
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    private ViewDragHelper.Callback callback = new ViewDragHelper.Callback() {
        //何时开始检测触摸事件
        @Override
        public boolean tryCaptureView(View child, int pointerId) {
            //如果当前触摸的child是mMainView时开始检测
            return mMainView == child;
        }
        //触摸到view后回调
        @Override
        public void onViewCaptured(View capturedChild, int activePointerId) {
            super.onViewCaptured(capturedChild, activePointerId);
        }
        //当拖拽状态改变，比如idle， dragging
        @Override
        public void onViewDragStateChanged(int state) {
            super.onViewDragStateChanged(state);
        }
        //当位置改变时调用，常用来与滑动时更改scale等
        @Override
        public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
            super.onViewPositionChanged(changedView, left, top, dx, dy);
        }
        //处理垂直滑动
        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            return 0;
        }
        //处理水平滑动
        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            return left;
        }
        //拖动结束后回调

        @Override
        public void onViewReleased(View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            //手指抬起后缓慢移动到指定位置
            if (mMainView.getLeft() < 500) {
                //关闭菜单
                //相当于Scroller的startScroll方法
                mViewDragHelper.smoothSlideViewTo(mMainView, 0, 0);
                ViewCompat.postInvalidateOnAnimation(DragViewGroup.this);
            } else {
                //打开菜单
                mViewDragHelper.smoothSlideViewTo(mMainView, 300, 0);
                ViewCompat.postInvalidateOnAnimation(DragViewGroup.this);
            }
        }
    };
    //重写computeScroll()方法，因为ViewDragHelper内部也是通过Scroller来实现平滑移动的
    //通常情况下可以使用以下所示模板代码
    @Override
    public void computeScroll() {
        if (mViewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this);
        }
    }
}
