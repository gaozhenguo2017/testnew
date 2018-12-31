package com.example.neo.mytest;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;


public class Video_Process {
    private MediaMetadataRetriever media_for_frame;
    private String video_process_filename;

    public Video_Process(String thread_filename) {
        video_process_filename = thread_filename;
        media_for_frame = new MediaMetadataRetriever();           //读取视频帧的类
        media_for_frame.setDataSource(video_process_filename);             //设置对象
        String time = media_for_frame.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);            //获取视频长度
        int duration = Integer.valueOf(time);//int类型时长
        int num_frame=duration/33;
        int array[][][] = new int[num_frame][352][3];               //第一维  时间，y，x，3通道   视频352宽，288高      照片288宽，352高
         int timesMS=33;                                         // 1000ms/25=40
        for(int i=0;i<num_frame;i++){                                      //获得所有帧的中间列
           int[][]temp= getin_memory(timesMS+=33);
            for(int h=0;h<352;h++){
                array[i][h][0]=temp[h][0];
                array[i][h][1]=temp[h][1];
                array[i][h][2]=temp[h][2];
            }
        }

    }
//    private time_filter(){
//        byte array[][][]=new  byte[][][];
//    }




    private int[][] getin_memory(int time){
        media_for_frame = new MediaMetadataRetriever();           //读取视频帧的类
        media_for_frame.setDataSource(video_process_filename);             //设置对象
        int width = Integer.valueOf(media_for_frame.extractMetadata(android.media.MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH));//宽
        int height = Integer.valueOf(media_for_frame.extractMetadata(android.media.MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT));//高
        Bitmap bitmap;
        int[][] ints =new int [352][3];                                    //图片的高
        bitmap = media_for_frame.getFrameAtTime(time*1000, MediaMetadataRetriever.OPTION_CLOSEST);      //获取i时刻bitmap
        int[] pixels = new int[width*height];//保存所有的像素的数组，图片宽×高
        bitmap.getPixels(pixels,0,bitmap.getWidth(),0,0,bitmap.getWidth(),bitmap.getHeight());                                           //获取143列像素数据
        for(int i = 143 ,h=0; i < pixels.length; i+=288,h++){
            int clr = pixels[i];
            int  red   = ((clr & 0x00ff0000) >> 16);  //取高两位
            int  green = ((clr & 0x0000ff00) >> 8); //取中两位
            int  blue  =  (clr & 0x000000ff); //取低两位
                    ints[h][0]=red;
                    ints[h][1]=green;
                    ints[h][2]=blue;
        }
        return ints;
    }
//    public void get_everyframe() {
//        media_for_frame = new MediaMetadataRetriever();           //读取视频帧的类
//        Bitmap bitmap;
//        File file;                                                 //图片文件
//        FileOutputStream out;                                     //输入流缓存
//        media_for_frame.setDataSource(video_process_filename);             //设置对象
//        String time = media_for_frame.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);            //获取视频长度
//        long duration = Long.valueOf(time);//long类型时长
//        System.out.println(duration);
//        if (Environment.getExternalStorageState().equals( Environment.MEDIA_MOUNTED)) // 判断是否可以对SDcard进行操作
//        {	  // 获取SDCard指定目录下
//            String  sdCardDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)+ "/TempImage/";
//            File dirFile  = new File(sdCardDir);  //目录转化成文件夹
//            if (!dirFile .exists()) {				//如果不存在，那就建立这个文件夹i
//                dirFile .mkdirs();
//            }							//文件夹有啦，就可以保存图片啦
//        for (long i = 0; i < duration; i += 33) {                                 //将Record文件夹里的文件信息都保持起来
//            bitmap = media_for_frame.getFrameAtTime(i*1000, MediaMetadataRetriever.OPTION_CLOSEST);
//                file = new File(sdCardDir, System.currentTimeMillis()+".jpg");// 在SDcard的目录下创建图片文,以当前时间为其命名
//                try {
//                    out = new FileOutputStream(file);
//                    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
//
//            }
//
//
//
//        }
//    }
}
