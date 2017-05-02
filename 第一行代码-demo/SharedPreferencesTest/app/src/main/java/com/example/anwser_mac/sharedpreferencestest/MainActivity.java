package com.example.anwser_mac.sharedpreferencestest;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import static android.R.attr.button;
import static android.R.attr.value;

public class MainActivity extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);


        Button button = (Button) findViewById(R.id.save_data);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获得SharedPreferences对象
                SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                //写如要保存的数据
                editor.putString("name", "Tom");
                editor.putInt("age", 28);
                editor.putBoolean("married", false);
                //调用apply()方法
                editor.apply();
            }
        });

        Button button1 = (Button) findViewById(R.id.restore_data);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
                String name = pref.getString("name", "");
                int age = pref.getInt("age", 0);
                boolean married = pref.getBoolean("married", false);
                Log.d("MainActivity","name is " + name);
                Log.d("MainActivity", "age is" + age);
                Log.d("MainActivity", "married is" + married);
            }
        });

        //创表
        dbHelper = new MyDatabaseHelper(this, "BookStore.db", null, 2);
        Button button2 = (Button) findViewById(R.id.create_database);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase writableDatabase = dbHelper.getWritableDatabase();
                writableDatabase.close();
            }
        });

        //添加数据
        Button addData = (Button) findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                //组装第一条数据
                values.put("name", "The Da Vinci Code");
                values.put("author", "Dan Brown");
                values.put("pages", 454);
                values.put("price", 16.69);
                db.insert("Book", null, values);    //插入第一条数据
                values.clear();
                //组装第二条数据
                values.put("name", "The Lost Symbol");
                values.put("author", "Dan Brown");
                values.put("pages", 510);
                values.put("price", 19.95);
                db.insert("Book", null, values);//插入第二条数据
            }
        });

        //更新数据
        Button updateData = (Button) findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("price", 1.99);
                //将名字是The Da Vinci Code的书价格改为1.99
                db.update("Book", values, "name = ?",new String[] {"The da Vinci Code"});
            }
        });

        //删除数据
        Button deleteData = (Button) findViewById(R.id.delete_data);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                //删除Book表中page大于500的数据
                db.delete("Book", "page > ?0", new String[] {"500"});
            }
        });

        //查询数据
        Button querryData = (Button) findViewById(R.id.querry_data);
        querryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                //查询Book表中的所有数据
                Cursor cursor = db.query("Book", null, null, null, null, null, null);
                //调用moveToFirst()方法将数据指针移动到第一行位置
                if (cursor.moveToFirst()) {
                    do {
                        //遍历Cursor对象，取出数据并打印
                        String name = cursor.getString(cursor.getColumnIndex("name"));   //getColumnIndex()方法可以获得某一列在表中对应的位置索引
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d("MainActivity", "book name is + " + name);
                        Log.d("MainActivity", "book author is + " + author);
                        Log.d("MainActivity", "book page is + " + pages);
                        Log.d("MainActivity", "book price is + " + price);
                    } while (cursor.moveToNext());
                }
                cursor.close();
            }
        });

        //替换数据
        Button replaceData = (Button) findViewById(R.id.replace_data);
        replaceData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                //开启事务
                db.beginTransaction();
                try {
                    db.delete("Book", null, null);
                    if (true) {
                        //在这里手动跑出一个异常，演示当添加不成功时，事务失败
                        throw new NullPointerException();
                    }
                    ContentValues values = new ContentValues();
                    values.put("name", "Game of Thrones");
                    values.put("author", "me");
                    values.put("pages", 750);
                    values.put("price", 20.22);
                    db.insert("Book", null, values);
                    db.setTransactionSuccessful();//事务执行成功
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    //结束事务
                    db.endTransaction();
                }

            }
        });
    }
}





















