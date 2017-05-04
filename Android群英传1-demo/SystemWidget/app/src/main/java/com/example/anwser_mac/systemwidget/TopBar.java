package com.example.anwser_mac.systemwidget;

import android.content.ContentProvider;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by anwser_mac on 2017/5/2.
 */

public
class
TopBar
        extends RelativeLayout
{
    //topbar上的元素：左按钮、右按钮、标题
    private Button mLeftButton, mRightButton;
    private TextView mTitleView;

    //布局属性，用来控制组件元素在ViewGroup中的位置
    private LayoutParams mLeftParams, mTitleParams, mRightParams;

    //左按钮饿属性值，即我们在att.xml文件中定义的属性
    private int mLeftTextColor;
    private Drawable mLeftBackground;
    private String mLeftText;

    //右按钮属性值，
    private int mRightTextColor;
    private Drawable mRightBackground;
    private String mRightText;
    //标题的属性值，即我们在atts.xml文件中定义的属性
    private float mTitleTextSize;
    private int mTitleTextColor;
    private String mTitle;

    //映射传入的接口对象
    private topbarClickListener mListener;

    public TopBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public TopBar(Context context) {
        super(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        //设置topbar的背景
        setBackgroundColor(0xFFF59563);
        //通过这个方法，将你在atts.xml中定义的declare-stylable
        //的所有属性值存储到TypeArray中
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.TopBar);
        //从TypedArray中取出对应的值来设置属性
        mLeftTextColor = ta.getColor(R.styleable.TopBar_leftTextColor, 0);
        mLeftBackground = ta.getDrawable(R.styleable.TopBar_leftBackground);
        mLeftText = ta.getString(R.styleable.TopBar_leftText);

        mRightTextColor = ta.getColor(R.styleable.TopBar_rightTextColor, 0);
        mRightBackground = ta.getDrawable(R.styleable.TopBar_rightBackground);
        mRightText = ta.getString(R.styleable.TopBar_rightText);

        mTitleTextSize = ta.getDimension(R.styleable.TopBar_titleTextSize, 10);
        mTitleTextColor = ta.getColor(R.styleable.TopBar_titleTextColor, 0);
        mTitle = ta.getString(R.styleable.TopBar_title);

        //获取完TypedArray的值后，一般要调用recyle()方法来避免重新创建的时候错误
        ta.recycle();

        mLeftButton = new Button(context);
        mRightButton = new Button(context);
        mTitleView = new TextView(context);

        //为创建的组件元素赋值
        //值来源于我们在引用xml文件中给对应的属性赋值
        mLeftButton.setTextColor(mLeftTextColor);
        mLeftButton.setBackground(mLeftBackground);
        mLeftButton.setText(mLeftText);

        mRightButton.setTextColor(mRightTextColor);
        mRightButton.setBackground(mRightBackground);
        mRightButton.setText(mRightText);

        mTitleView.setText(mTitle);
        mTitleView.setTextColor(mTitleTextColor);
        mTitleView.setTextSize(mTitleTextSize);
        mTitleView.setGravity(Gravity.CENTER);

        //为组件元素设置相应的布局元素
        mLeftParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mLeftParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT,TRUE);
        //添加到viewgroup
        addView(mLeftButton, mLeftParams);

        mRightParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mRightParams.addRule(ALIGN_PARENT_RIGHT, TRUE);
        addView(mRightButton, mRightParams);

        mTitleParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mTitleParams.addRule(CENTER_IN_PARENT, TRUE);
        addView(mTitleView,mTitleParams);

        //按钮的点击事件，调用接口回调
        mRightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.rightClick();
            }
        });

        mLeftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.leftClick();
            }
        });
    }


    //暴露一个方法给调用着来注册接口回调
    //通过接口来获得回调者对接口方法的实现
    public void setOnTopbarClickListener(topbarClickListener mListener) {
        this.mListener = mListener;
    }

    //设置按钮的显示与否，通过id区分按钮，flag区分是否显示
    public void setButtonVisable(int id, boolean flag) {
        if (flag) {
            if (id == 0) {
                mLeftButton.setVisibility(VISIBLE);
            } else {
                mRightButton.setVisibility(VISIBLE);
            }
        } else {
            if (id == 0) {
                mLeftButton.setVisibility(GONE);
            } else {
                mRightButton.setVisibility(GONE);
            }
        }
    }

    //接口对象
    public interface topbarClickListener {
        //左按钮点击事件
        void leftClick();
        //右按钮点击事件
        void rightClick();
    }

}
