package com.example.anwser_mac.notificationtest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;

import static android.R.string.no;

public class MainActivity extends AppCompatActivity {

    private Button sendNotice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendNotice = (Button) findViewById(R.id.send_notification);
        sendNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获得NotificationManager对象
//                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//                //创建Notification
//                Notification notification = new NotificationCompat.Builder(MainActivity.this)
//                        .setContentTitle("这是通知内容标题")
//                        .setContentText("正式通知内容")
//                        .setWhen(System.currentTimeMillis())
//                        .setSmallIcon(R.mipmap.ic_launcher)
//                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
//                        .build();
//                //发送通知
//                manager.notify(1, notification);

                //初始化Intent
                Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
                //使用Intent获得PendingIntent对象
                PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Notification notification = new NotificationCompat.Builder(MainActivity.this)
                        .setContentTitle("这是通知内容标题")
                        .setContentText("正式通知内容")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        //设置PendingIntent
                        .setContentIntent(pi)
                        //设置点击通知时删除通知
                        .setAutoCancel(true)
                        //播放音频
                        .setSound(Uri.fromFile(new File("/system/media/audio/ringtones/Luna.ogg")))
                        //设置手机接收到通知时立刻振动1秒，静止1秒，再振动一秒
                        .setVibrate(new long[] {0, 1000, 1000, 1000})
                        //设置LED灯,绿色闪烁
                        .setLights(Color.GREEN, 1000, 1000)
//                        .setStyle(new NotificationCompat.BigTextStyle().bigText("hhhhhhhhhhhhhhhhhhhhhhhhhhhhzheshi 一段很长长的文本"))
                        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher_round)))
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .build();
                //显示通知
                manager.notify(1,notification);
            }

        });
    }


}
