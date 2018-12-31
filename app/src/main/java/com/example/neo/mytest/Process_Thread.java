package com.example.neo.mytest;

public class Process_Thread extends Thread {
    private String thread_filename;
    private Video_Process video_process;
    public Process_Thread(String name) {
      thread_filename =name;
    }

    @Override
    public void run() {
        video_process=new Video_Process(thread_filename);
       // video_process.get_everyframe();
    }
}
