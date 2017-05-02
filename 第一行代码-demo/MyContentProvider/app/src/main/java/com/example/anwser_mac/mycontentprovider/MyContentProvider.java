package com.example.anwser_mac.mycontentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by anwser_mac on 2017/4/12.
 */

public class MyContentProvider extends ContentProvider {

    public static final int TABLE1_DIR = 0;

    public static final int TABLE1_ITEM = 1;

    public static final int TABLE2_DIR = 2;

    public static final int TABLE2_ITEM = 3;

    private static UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.example.app.provider", "table1", TABLE1_DIR);
        uriMatcher.addURI("com.example.app.provider", "table1/#", TABLE1_ITEM);
        uriMatcher.addURI("com.example.app.provider", "table2", TABLE2_DIR);
        uriMatcher.addURI("com.example.app.provider", "table2/#", TABLE2_ITEM);

    }

    ///初始化内容提供器时调用
    //通常会在这里完成对数据库的创建和升级等操作
    //返回true表示初始化成功，false为失败
    //注：只有当ContentResolver尝试访问程序的数据时，内容提供器才会被初始化。
    @Override
    public boolean onCreate() {
        return false;
    }


    ///从内容提供器中查询数据
    //uri : 确定查询哪张表
    //projection : 确定查询哪些列
    //selection 和 selectionArgs : 约束查询哪些行
    //sortOrder ： 用于对结果进行排序
    //return  :  Cursor对象，存放查询结果
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        switch (uriMatcher.match(uri)) {
            case TABLE1_DIR:
                //查询table1表中的所有数据
                break;
            case TABLE1_ITEM:
                //查询table1表中的单条数据
                break;
            case TABLE2_DIR:
                //查询table2表中的所有数据
                break;
            case TABLE2_ITEM:
                //查询table2表中的单条数据
                break;
        }
        return null;
    }

    ///向内容提供器中添加一条数据
    //uri ：确定添加到哪张表
    //values ：要添加的数据，保存在ContentValues中
    //return  ：  添加完成后，返回一个用于表示这条新纪录的URI
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }


    ///更新内容提供器中已有的数据
    //uri : 确定更新哪一张表的数据
    //values  :  新的数据，保存在ContentValues中
    //selection 和 selectionArgs ：用于约束更新哪些行。
    //return ：受影响的行数
    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    ///从内容提供器中删除数据
    //uri ： 确定要删除哪一张表的数据
    //selection 和 selectionArgs : 用于约束删除哪些行
    //return ：删除的行数
    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }


    ///根据传入的URI来返回相应的MIME类型
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)) {
            case TABLE1_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.app.provider.table1";
            case TABLE1_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.app.provider.table1";
            case TABLE2_DIR:
                return "vnd.android.cursor.dir/";
            case TABLE2_ITEM:
                return "vnd.android.cursor.item/vnd.com.example.app.provider.table2";
            default:
                break;
        }
        return null;
    }
}
