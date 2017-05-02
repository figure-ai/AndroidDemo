package com.example.anwser_mac.androidthreadtest;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView text;

    public static final int UPDATE_TEXT = 1;

    //定义一个Handler对象，并重写父类的handleMessage()方法。
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_TEXT:
                    //在这里进行UI操作
                    if (text.getText().equals("我是要更改的文本")) {
                        text.setText("我是更改后的文本");
                    } else {
                        text.setText("我是要更改的文本");
                    }
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        text = (TextView) findViewById(R.id.text);
        Button changeText = (Button) findViewById(R.id.change_text);
        changeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //初始化Message实例
                        Message message = new Message();
                        //设置message的what字段值
                        message.what = UPDATE_TEXT;
                        //将message发送出去
                        handler.sendMessage(message);
                    }
                }).start();
            }
        });
    }
}


