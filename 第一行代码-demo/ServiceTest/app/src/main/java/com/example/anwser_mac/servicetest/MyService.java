package com.example.anwser_mac.servicetest;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.annotation.IntDef;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import static android.os.Build.VERSION_CODES.M;

public class MyService extends Service {

    private DownloadBinder mBinder;

    class DownloadBinder extends Binder {
        public void startDownload() {
//            Toast.makeText(MyService.this, "开始下载", Toast.LENGTH_SHORT).show();
            Log.d("MyService", "startDownload executed");
        }
        public int getProgress() {
//            Toast.makeText(MyService.this, "正在读取下载进度", Toast.LENGTH_SHORT).show();
            Log.d("MyService", "getProgress executed");
            return 0;
        }

    }

    public MyService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mBinder;
    }

    ///服务被创建时调用
    @Override
    public void onCreate() {
        super.onCreate();
//        Toast.makeText(this, "服务已创建", Toast.LENGTH_SHORT).show();
        Log.d("MyService", "onCreate executed");
        //创建一个前台服务
        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("This is content title")
                .setContentText("This is content text")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pi)
                .build();
        startForeground(1, notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "服务已开启", Toast.LENGTH_SHORT).show();
//
        Log.d("MyService", "onStartCommand executed");
        ///开启一个子线程处理耗时操作
        new Thread(new Runnable() {
            @Override
            public void run() {
                //处理具体操作
                stopSelf();
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    ///当服务被销毁时调用
    @Override
    public void onDestroy() {
//        Toast.makeText(this, "服务已停止", Toast.LENGTH_SHORT).show();
        super.onDestroy();
        Log.d("MyService", "onDestroy executed");
    }
}
