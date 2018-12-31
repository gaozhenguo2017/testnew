package com.example.neo.mytest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileActivity extends AppCompatActivity {
    private List<String> itemsList;           //保存文件信息
    private ListView listView;                //定义listView用来显示adapter
    private MBaseAdapter mBaseAdapter;             //定义适配器
    private List<Bitmap>bitmapslist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        intview();
    }

    private void intview() {
        listView = findViewById(R.id.list);
        itemsList = new ArrayList<>();                          //给参数赋值
        bitmapslist = new ArrayList<>();
        mBaseAdapter =new MBaseAdapter(this,itemsList,bitmapslist);         //设置适配器
        File sampleDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "Record");         //sampleDir是Record文件夹
        if (!sampleDir.exists()) {
            sampleDir.mkdirs();
        }
       final String  path =sampleDir.getAbsolutePath();                                            //Record绝对路径
        Bitmap bitmap;                                                                       //每个文件缩略图
        File[] files = sampleDir.listFiles();                                       //读取Record文件夹里文件
        MediaMetadataRetriever media_for_fisrt = new MediaMetadataRetriever();           //读取视频帧的类
        for (int i = 0; i < files.length; i++) {                                 //将Record文件夹里的文件信息都保持起来
            File f = files[i];
            itemsList.add(f.getName());                                           //读入视频名到itemlist
            media_for_fisrt.setDataSource(path+"/"+f.getName());                     //读取视频预览图
            bitmap = media_for_fisrt.getFrameAtTime();
            bitmapslist.add(bitmap);

        }
        listView.setAdapter(mBaseAdapter);       //设置adapter
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               switch (view.getId()) {
                   case R.id.video_start:
                       Toast.makeText(FileActivity.this, itemsList.get(position), Toast.LENGTH_SHORT).show();
                       break;
                   case R.id.process_start:
                       Toast.makeText(FileActivity.this, itemsList.get(position)+"sggs", Toast.LENGTH_SHORT).show();
                       break;
                   default:                                                  //启动serice
                       //    Toast.makeText(FileActivity.this, itemsList.get(position), Toast.LENGTH_SHORT).show();
                       Intent startIntent = new Intent(FileActivity.this, My_Service.class);
                       startIntent.putExtra("filename",path+"/"+itemsList.get(position));              //传入文件路径及文件名
                       startService(startIntent);
               }

            }
        });
    }
}

