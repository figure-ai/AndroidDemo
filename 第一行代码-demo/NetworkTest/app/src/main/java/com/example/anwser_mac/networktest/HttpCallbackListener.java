package com.example.anwser_mac.networktest;

/**
 * Created by anwser_mac on 2017/4/14.
 */

///定义一个接口
public interface HttpCallbackListener {
    //网络请求成功时的回调
    void onFinish(String response);
    //网络请求失败时的回调
    void onError(Exception e);
}

