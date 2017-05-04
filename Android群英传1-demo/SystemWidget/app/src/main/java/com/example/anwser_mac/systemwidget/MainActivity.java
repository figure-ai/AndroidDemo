package com.example.anwser_mac.systemwidget;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button topBar = (Button) findViewById(R.id.btnTopBar);
        topBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TopBaruTest.class);
                intent.putExtra("flag", 1);
                startActivity(intent);
            }
        });

        Button circleViewBtn = (Button) findViewById(R.id.btncircleView);
        circleViewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyTestView.class);
                intent.putExtra("flag", 0);
                startActivity(intent);
            }
        });

        Button volumebtn = (Button) findViewById(R.id.btnVolumeView);
        volumebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyTestView.class);
                intent.putExtra("flag", 2);
                startActivity(intent);
            }
        });

        Button scrollView = (Button) findViewById(R.id.btnMyScrollView);
        scrollView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyTestView.class);
                intent.putExtra("flag", 3);
                startActivity(intent);
            }
        });

    }
}
