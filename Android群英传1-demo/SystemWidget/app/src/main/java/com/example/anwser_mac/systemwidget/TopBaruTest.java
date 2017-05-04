package com.example.anwser_mac.systemwidget;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by anwser_mac on 2017/5/2.
 */

public
class
TopBaruTest extends AppCompatActivity {

    private TopBar mTopbat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topbar_test);
        //获得我们创建的topbar
        mTopbat = (TopBar) findViewById(R.id.topBar);
        //为topba注册佳宁事件，
        mTopbat.setOnTopbarClickListener(new TopBar.topbarClickListener() {
            @Override
            public void leftClick() {
                Toast.makeText(TopBaruTest.this, "left", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void rightClick() {
                Toast.makeText(TopBaruTest.this, "right", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
