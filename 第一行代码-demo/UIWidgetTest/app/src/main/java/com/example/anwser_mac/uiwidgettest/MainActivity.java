package com.example.anwser_mac.uiwidgettest;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.media.Image;
import android.preference.DialogPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        Button btn = (Button) findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获得输入框的内容
//                EditText field = (EditText) findViewById(R.id.editText);
//                String string = field.getText().toString();
//                Toast.makeText(MainActivity.this, string, Toast.LENGTH_LONG).show();
                //获得imageView的图片
//                ImageView imgV = (ImageView) findViewById(R.id.image_view);
//                imgV.setImageResource(R.drawable.icon_camera);
                //控制进度条的相关属性
//                ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
//                int progress = progressBar.getProgress();
//                progress = progress + 10;
//                progressBar.setProgress(progress);
                //控制进度条的隐藏与否
//                if (progressBar.getVisibility() == View.GONE) {
//                    progressBar.setVisibility(View.VISIBLE);
//                } else {
//                    progressBar.setProgress(View.GONE);
//                }
                //创建一个警告框
//                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
//                dialog.setTitle("我是警告框");
//                dialog.setMessage("我是警告信息");
//                dialog.setCancelable(false);
//                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(MainActivity.this, "点击Ok", Toast.LENGTH_LONG).show();
//                    }
//                });
//                dialog.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Toast.makeText(MainActivity.this, "点击Cancle", Toast.LENGTH_LONG).show();
//                    }
//                });
//                dialog.show();
                //创建一个ProgressDialog
                ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setTitle("这是一个ProgressDialog");
                progressDialog.setMessage("Loading...");
                progressDialog.setCancelable(true);
                progressDialog.show();
            }
        });
    }
}
