package com.example.anwser_mac.dragviewtest;

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
        Button dragView = (Button) findViewById(R.id.btn_dragview);
        dragView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DragVeiwActivity.class));
            }
        });

        final Button dragviewgroup = (Button) findViewById(R.id.btn_dragviewgroup);
        dragviewgroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DragViewGroupTest.class));
            }
        });
    }
}
