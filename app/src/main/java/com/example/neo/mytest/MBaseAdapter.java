package com.example.neo.mytest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MBaseAdapter extends BaseAdapter {
    private List<String> items;                   //外部传入的名字和缩略图
    private List<Bitmap>bitmaps;
    private LayoutInflater mInflater;
    private Bitmap mIcon1;                      //存放两个按钮位图
    private Bitmap mIcon2;
    public MBaseAdapter(Context context, List<String> it, List<Bitmap> bi) {
        items = it;
        bitmaps = bi;
        mInflater = LayoutInflater.from(context);
        mIcon1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.icon_vidstart);
        mIcon2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.icon_refactor);
    }
    @Override
    public int getCount() {                              //视频个数
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;                                                     //和convertView连用，在多次绘制组件时节省资源
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.file_item, null);             //用file_item.xml来进行填充
            holder = new ViewHolder();
            holder.text_view = (TextView) convertView.findViewById(R.id.text);               //绑定给
            holder.image_view = (ImageView) convertView.findViewById(R.id.preview);
            holder.image_button_vid=(ImageView)convertView.findViewById(R.id.video_start);      //容器
            holder.image_button_pro= (ImageView)convertView.findViewById(R.id.process_start);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.image_view.setImageBitmap(bitmaps.get(position));                     //控件显示图片文字
        holder.text_view.setText(items.get(position));
        holder.image_button_vid.setImageBitmap(mIcon1);
        holder.image_button_pro.setImageBitmap(mIcon2);
        return convertView;
    }
    private class ViewHolder {
        TextView text_view;
        ImageView image_view;
        ImageView image_button_vid;
        ImageView image_button_pro;
    }
}
