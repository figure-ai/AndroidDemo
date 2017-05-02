package com.example.anwser_mac.networktest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

import javax.xml.parsers.SAXParserFactory;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.R.string.ok;

public class MainActivity extends AppCompatActivity {

    TextView responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        Button sentRequest = (Button) findViewById(R.id.send_request);
        responseText = (TextView) findViewById(R.id.response_text);
        sentRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                sentRequestWithHttpURLConnection();
//                sendRequestWithOkHttp();
                String address = "http://10.0.2.2/get_data.json";

//                HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
//                    @Override
//                    public void onFinish(String response) {
//                        showRespons(response);
//                    }
//                    @Override
//                    public void onError(Exception e) {
//
//                    }
//                });

                HttpUtil.sendOkHttpRequest(address, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        //请求失败时调用
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        //收到服务器返回的数据调用
                        String responseData = response.body().string();
                        showRespons(responseData);
                    }
                });
            }
        });
    }

    private void sendRequestWithOkHttp() {
        //在子线程中发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
//                            .url("http://10.0.2.2/get_data.xml")
                            .url("http://10.0.2.2/get_data.json")
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    showRespons(responseData);
                    //解析XML数据
//                    parseXMLWithPull(responseData);
//                    parseXMLWithSAX(responseData);
                    //解析json数据
//                    parseJSONWithJSONObject(responseData);
                    parseJSONWithGSON(responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //使用谷歌提供的开源库GSON解析json数据
    private void parseJSONWithGSON(String jsonData) {
        Gson gson = new Gson();
        //将json数组转换为模型数组
        List<App> appList = gson.fromJson(jsonData, new TypeToken<List<App>>(){}.getType());
        //遍历模型数组
        for (App app : appList) {
            Log.d("MainActivity", "gson id is " + app.getId());
            Log.d("MainActivity", "gson name is " + app.getName());
            Log.d("MainActivity", "gson version is " + app.getVersion());
        }
    }

    ///使用官方的JSONObject解析json数据
    private void parseJSONWithJSONObject(String jsonData) {
        try {
            //将服务器返回的json数据转换为json数组
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                //获得对应的json对象
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String version = jsonObject.getString("version");
                Log.d("MainActivity", "id is " + id);
                Log.d("MainActivity", "name is " + name);
                Log.d("MainActivity", "version is " + version);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ///通过SAX方式解析XML数据
    private void parseXMLWithSAX(String xmlData) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            XMLReader xmlReader = factory.newSAXParser().getXMLReader();
            ContentHandler handler = new ContentHandler();
            //将Contentdler的实例设置到XMLReader中
            xmlReader.setContentHandler(handler);
            //开始执行解析
            xmlReader.parse(new InputSource(new StringReader(xmlData)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //通过Pull方式解析xml数据
    private void parseXMLWithPull(String xmlData) {
        try {
            //1. 获取XmlPullParserFactory实例
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            //2. 借助XmlPullParserFactory实例获取到XmlPullParser对象
            XmlPullParser xmlPullParser = factory.newPullParser();
            //3. 调用XmlPullParser的setInput()方法，就可以对服务器返回的XML数据进行解析。
            xmlPullParser.setInput(new StringReader(xmlData));
            //4. 通过getEventType()可以得到当前的解析事件
            int eventType = xmlPullParser.getEventType();
            String id = "";
            String name = "";
            String version = "";
            //5. 在一个while循环中解析数据，如果eventType不等于XmlPullParser.END_DOCUMENT说明解析工作还没有完成
            while (eventType != XmlPullParser.END_DOCUMENT) {
                //5.1 通过调用getName()方法得到当前节点的名字
                String nodeName = xmlPullParser.getName();
                switch (eventType) {
                    //开始解析某个节点
                    case XmlPullParser.START_TAG: {
                        if ("id".equals(nodeName)) {
                            //通过调用nextText()方法获得节点的内容
                            id = xmlPullParser.nextText();
                        } else if("name".equals(nodeName)) {
                            name = xmlPullParser.nextText();
                        } else if("version".equals(nodeName)) {
                            version = xmlPullParser.nextText();
                        }
                        break;
                    }
                    //完成解析某个节点
                    case XmlPullParser.END_TAG: {
                        if ("app".equals(nodeName)) {
                            Log.d("MainActivity", "id is " + id);
                            Log.d("MainActivity", "name is " + name);
                            Log.d("MainActivity", "version is " + version);
                        }
                        break;
                    }
                    default:
                        break;
                }
                eventType = xmlPullParser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void sentRequestWithHttpURLConnection() {
        //开启线程发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    //初始化url
                    URL url = new URL("http://guolin.tech/api/china/23/223");
                    //初始化connection
                    connection = (HttpURLConnection) url.openConnection();
                    //设置请求方法
                    connection.setRequestMethod("GET");
                    //设置请求超时限制
                    connection.setConnectTimeout(8000);
                    //设置读取超时限制
                    connection.setReadTimeout(8000);
                    //获取服务器返回的输入流
                    InputStream in = connection.getInputStream();
                    //下面对获取到的输入流进行读取
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    showRespons(response.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

    private void showRespons(final String respons) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //在这里进行UI操作，将结果显示在界面上
                responseText.setText(respons);
            }
        });
    }
}
