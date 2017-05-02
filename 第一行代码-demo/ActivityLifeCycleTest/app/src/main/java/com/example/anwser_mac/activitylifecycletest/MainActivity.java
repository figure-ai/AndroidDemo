package com.example.anwser_mac.activitylifecycletest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //监听按钮点击事件
        Button startNormalActivity = (Button) findViewById(R.id.start_normal_activity);
        Button startDialogActivity = (Button) findViewById(R.id.start_dialog_activity);

        startNormalActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NormalAcitivity.class);
                startActivity(intent);
            }
        });

        startDialogActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DialogAcitivity.class);
                startActivity(intent);
            }
        });

        if (savedInstanceState != null) {
            //恢复被销毁前保存的数据
            String tempData = savedInstanceState.getString("data_key");
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String tempData = "Something you just typed";
        //使用putString()方法保存字符串，使用putInt()方法保存整型数据
        //第一个参数是键，第二个参数是值
        outState.putString("data_key", tempData);
    }

    @Override
    protected void onStart() {
        Toast.makeText(MainActivity.this, "MainActivity onStart", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        Toast.makeText(MainActivity.this, "MainActivity onResume", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPause() {
        Toast.makeText(MainActivity.this, "MainActivity onPause", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        Toast.makeText(MainActivity.this, "MainActivity onStop", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        Toast.makeText(MainActivity.this, "MainActivity onDestroy", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onRestart() {
        Toast.makeText(MainActivity.this, "MainActivity onRestart", Toast.LENGTH_LONG).show();
    }
}
