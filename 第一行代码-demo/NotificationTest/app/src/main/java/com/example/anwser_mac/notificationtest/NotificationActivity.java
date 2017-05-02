package com.example.anwser_mac.notificationtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification_layout);
        //获得NotificationManager对象
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //取消id为1的通知
        manager.cancel(1);
    }
}
