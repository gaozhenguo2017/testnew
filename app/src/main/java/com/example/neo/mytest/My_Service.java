package com.example.neo.mytest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class My_Service extends Service {
    private String filename;                       //传入文件名

    @Override
    public void onCreate() {
        super.onCreate();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        filename =intent.getStringExtra("filename");                 //传递点了哪个视频
        Thread process_thread = new Process_Thread(filename);                     //filename传给线程
        process_thread.start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
