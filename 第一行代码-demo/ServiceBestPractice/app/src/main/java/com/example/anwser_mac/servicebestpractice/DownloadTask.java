package com.example.anwser_mac.servicebestpractice;

import android.app.DownloadManager;
import android.app.VoiceInteractor;
import android.os.AsyncTask;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by anwser_mac on 2017/4/19.
 */

public
class
    //三个泛型参数
    //第一个：传给后台任务
    //第二个：作为进度显示单位
    //第三个：反馈执行的结果
DownloadTask extends AsyncTask<String, Integer, Integer> {
    //定义4个整型常量用于表示下载的状态
    public static final int TYPE_SUCCESS = 0;
    public static final int TYPE_FAILED = 1;
    public static final int TYPE_PAUSED = 2;
    public static final int TYPE_CANCELED = 3;

    private DownloadListenner listenner;

    private boolean isCancele = false;

    private boolean isPaused = false;

    private int lastProgress;

    //构造函数中要求传入一个DownloadListenner参数，作为下载状态的回调
    public DownloadTask(DownloadListenner listenner) {
        this.listenner = listenner;
    }

    //重写doInBackgound()，用于在后台执行具体的下载逻辑
    @Override
    protected Integer doInBackground(String... params) {
        InputStream is = null;
        RandomAccessFile saveFile = null;
        File file = null;
        try {
            //用来记录已下载的文件长度
            long downloadedLength = 0;
            //从参数中获取到下载的url地址
            String downloadUrl = params[0];
            //根据url解析出下载的文件名
            String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
            //指定将文件下载到Environment.DIRECTORY_DOWNLOADS，也就时SD卡的Download目录
            String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            file = new File(directory + fileName);
            //判断要下载的文件是否已存在
            if (file.exists()) {
                //如果已存在的，读取已下载的字节数，实现断点续传的功能
                downloadedLength = file.length();
//                downloadedLength = g
            }
            //调用getContentLength方法，获取带下载文件的总长度
            long contentLength = getContentLength(downloadUrl);
            if (contentLength == 0) {
                //如果文件长度为0，说明文件有问题，直接返回TYPE_FAILED;
                return TYPE_FAILED;
            } else if (contentLength == downloadedLength) {
                //如果已下载字节和文件总字节相等，说明已经下载完成了
                return TYPE_SUCCESS;
            }
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    //添加一个header用于告诉服务器从哪个字节开始下载，因为已下载的就不需要下载了
                    //这样就实现了断点续传的功能了
                    .addHeader("RANGE", "bytes=" + downloadedLength + "-")
                    .url(downloadUrl)
                    .build();
            //读取服务器响应的数据
            Response response = client.newCall(request).execute();
            if (response != null) {
                is = response.body().byteStream();
                saveFile = new RandomAccessFile(file, "rw");
                saveFile.seek(downloadedLength);//跳过已下载的字节
                byte[] b = new byte[1024];
                int total = 0;
                int len;
                //判断用户有没有在下载过程中出现暂停或取消的操作
                while ((len = is.read(b)) != -1) {
                    if (isCancele) {
                        return TYPE_CANCELED;
                    } else if (isPaused){
                        return TYPE_PAUSED;
                    } else {
                        //将从网络上读取的数据写入到本地
                        total += len;
                        //计算已下载的百分比
                        int progress = (int) ((total + downloadedLength) * 100 / contentLength);
                        saveFile.write(b, 0, len);
                        publishProgress(progress);
                    }
                }
                response.body().close();
                return TYPE_SUCCESS;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();;
                }
                if (saveFile != null) {
                    saveFile.close();
                }
                if (isCancele && file != null) {
                    file.delete();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return TYPE_FAILED;
    }
    //重写onProgressUpdate()用于在界面上更新当前的下载进度
    @Override
    protected void onProgressUpdate(Integer... values) {
        //从参数中获取到下载进度
        int progress = values[0];
        //如果进度有变化的话
        if (progress > lastProgress) {
            //回调onProgress()通知下载进度更新
            listenner.onProgress(progress);
            lastProgress = progress;
        }
    }
    //重写onPostExecute， 用于通知最终的下载结果
    @Override
    protected void onPostExecute(Integer status) {
        switch (status) {
            case TYPE_SUCCESS:
                listenner.onSuccess();
                break;
            case TYPE_FAILED:
                listenner.onFailed();
                break;
            case TYPE_PAUSED:
                listenner.onPaused();
                break;
            case TYPE_CANCELED:
                listenner.onCanceled();
                break;
        }
    }

    public void pauseDownload() {
        isPaused = true;
    }

    public void cancelDownload() {
        isCancele = true;
    }

    private long getContentLength(String downloadUrl) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(downloadUrl)
                .build();
        Response response = client.newCall(request).execute();
        if (response != null && response.isSuccessful()) {
            long contentLength = response.body().contentLength();
            response.close();
            return contentLength;
        }
        return 0;
    }
}
