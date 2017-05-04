package com.example.anwser_mac.mylistview;

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
        clickListener();
    }
    private void clickListener() {
        Button flexibleBtn = (Button) findViewById(R.id.btnFlexible);
        flexibleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FlexibleListViewTest.class));
            }
        });

        Button scrollHideBtn = (Button) findViewById(R.id.btnScrollHide);
        scrollHideBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ScrollHideList.class));
            }
        });

        Button focusBtn = (Button) findViewById(R.id.btnFoucus);
        focusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FocusListViewTest.class));
            }
        });
    }
}
