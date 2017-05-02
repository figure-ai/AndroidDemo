package com.example.anwser_mac.webviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        //获得webView
        WebView webView = (WebView) findViewById(R.id.web_view);
        //通过调用getSettings()设置浏览器的属性，这里的setJavaScriptEnabled()方法设置了，
        // webView支持JavaScript脚本
        webView.getSettings().setJavaScriptEnabled(true);
        //设置打开新的网页时仍在当前的webView打开，而不是去启动系统浏览器
        webView.setWebViewClient(new WebViewClient());
        //设置webView要加载的网页链接
        webView.loadUrl("http://www.baidu.com");
    }
}
