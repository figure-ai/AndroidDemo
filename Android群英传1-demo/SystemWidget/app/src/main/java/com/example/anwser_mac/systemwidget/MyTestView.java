package com.example.anwser_mac.systemwidget;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MyTestView extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int flag = getIntent().getIntExtra("flag", -1);
        switch (flag) {
            case 0:
                setContentView(R.layout.circleview);
                break;
            case 1:
                setContentView(R.layout.topbar_test);
                break;
            case 2:
                setContentView(R.layout.volume);
                break;
            case 3:
                setContentView(R.layout.myscrollview);
                break;
        }
    }
}
