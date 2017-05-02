package com.example.anwser_mac.recyclerviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private List<Fruit> fruitList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        //初始化水果数据
        initFruits();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        //设置RecyclerView滚动的方向
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        recyclerView.setLayoutManager(layoutManager);
        //实现瀑布流滚动
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        FruitAdapter adapter = new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);

    }

    private void initFruits() {
        for (int i = 0; i < 2; i++) {
            Fruit apple = new Fruit(getRandomLentgthName("apple"), R.drawable.apple_pic);
            fruitList.add(apple);

            Fruit bananna = new Fruit(getRandomLentgthName("Banana"), R.drawable.banana_pic);
            fruitList.add(bananna);

            Fruit orange = new Fruit(getRandomLentgthName("Orange"), R.drawable.orange_pic);
            fruitList.add(orange);

            Fruit watermelo = new Fruit(getRandomLentgthName("waterelon"), R.drawable.watermelon_pic);
            fruitList.add(watermelo);

            Fruit pear = new Fruit(getRandomLentgthName("Pear"), R.drawable.pear_pic);
            fruitList.add(pear);

            Fruit grape = new Fruit(getRandomLentgthName("Grape"), R.drawable.grape_pic);
            fruitList.add(grape);

            Fruit pineapple = new Fruit(getRandomLentgthName("Pineapple"), R.drawable.pineapple_pic);
            fruitList.add(pineapple);

            Fruit strawberry = new Fruit(getRandomLentgthName("cherry"), R.drawable.cherry_pic);
            fruitList.add(strawberry);
        }
    }

    private String getRandomLentgthName(String name) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(name);
        }
        return builder.toString();
    }
}
