package com.example.anwser_mac.sharedpreferencestest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Button;
import android.widget.Toast;

import static android.R.attr.id;
import static android.R.attr.name;

/**
 * Created by anwser_mac on 2017/4/11.
 */

class MyDatabaseHelper extends SQLiteOpenHelper {

    //Book表，存放书本的各类详细数据
    public static final String CREATE_BOOK2 = "create table Book("
            + "id integer primary key autoincrement, "
            + "author text, "
            + "price real, "
            + "pages integer, "
            + "name text)";

    //Category表：用于记录书籍的分类
    public static final String CREATE_GATEGORY = "create table Book ("+" id integer primary key autoincrement," +
            "category_name text, " +
            "category_code integer)";

    private Context mContext;

    //重写构造方法
    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

        super(context, name, factory, version);
        Toast.makeText(context, "创建", Toast.LENGTH_SHORT).show();
        mContext = context;
    }

    //重写onCreate方法
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK2);
        db.execSQL(CREATE_GATEGORY);
        Toast.makeText(mContext, "创建成功", Toast.LENGTH_SHORT).show();
    }

    //重写onUpgrade()方法
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //删除已存在的表
//        db.execSQL("drop table if exists Book");
//        db.execSQL("drop table if exists Category");
//        //调用onCreate()方法，创建要创建的表
//        onCreate(db);
        switch (oldVersion) {
            //对旧版本进行判断，执行新版本要更新的内容。这里需要注意，case后面不加break，以保证用户直接从第一版升级至第三版时case1和case2都会执行。
            case 1:
                db.execSQL(CREATE_GATEGORY);
            case 2:
                db.execSQL("alter table Book add colum category_id integer");
            default:
        }
    }

    //更新数据
}
