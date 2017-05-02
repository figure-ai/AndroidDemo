package com.example.anwser_mac.fragmenttest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "RightFragment";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        //给按钮添加一个点击事件
        Button button = (Button) findViewById(R.id.left_button);
        button.setOnClickListener(this);
        replaceFragment(new RightFragment());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.left_button:
                TextView textView = (TextView) findViewById(R.id.right_textview);
                //判断哪个碎片需要进行切换
                if (textView != null) {
                    replaceFragment(new AnotherRightFragment());
                } else {
                    replaceFragment(new RightFragment());
                }
        }
    }

    ///切换右边显示的碎片
    //fragment : 要切换的碎片
    private void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction tansaction = fragmentManager.beginTransaction();
        tansaction.replace(R.id.right_layout, fragment);
        tansaction.addToBackStack(null);
        tansaction.commit();
    }


}
