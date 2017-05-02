package com.example.anwser_mac.customviewtest;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * Created by anwser_mac on 2017/3/31.
 */

public class TitleLayout extends LinearLayout {
    //重写构造函数
    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        //LayoutInflater的from()方法可以构造一个LayoutInflater对象，然后调用inflate可以动态加载一个布局文件
        //inflate()方法接收两个参数,1.要加载的布局文件的id，2.加载好的布局添加一个父布局。
        LayoutInflater.from(context).inflate(R.layout.title, this);

        //监听返回按钮的点击事件
        Button titleBack = (Button) findViewById(R.id.title_back);
        Button titleEdit = (Button) findViewById(R.id.title_edit);
        titleBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //当前activity出栈
                ((Activity) getContext()).finish();
            }
        });
        //Edit按钮的点击事件
        titleEdit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "点击了编辑按钮", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
