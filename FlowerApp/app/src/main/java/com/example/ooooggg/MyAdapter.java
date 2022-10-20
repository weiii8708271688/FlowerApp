package com.example.ooooggg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.LayoutInflater;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.content.Context;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.RemoteViews;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.*;
import android.widget.BaseAdapter;
import android.widget.Toast;

public class MyAdapter extends BaseAdapter {
    //上下文
    Context context;
    //数据项
    private List<Map<String, Object>> data;
    public ImageView temp;
    public String[] string_file;
    ViewHolder[] viewHolder = new ViewHolder[100];
    public MyAdapter(Context context, List<Map<String, Object>> data){
        this.data = data;
        this.context = context;
    }
    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(context == null)
            context = viewGroup.getContext();
        if(view == null){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.message_string,null);
            viewHolder[i] = new ViewHolder();
            viewHolder[i].mBtn = (ImageButton)view.findViewById(R.id.mBtn);
            viewHolder[i].mtext1 = (TextView)view.findViewById(R.id.mtext1);
            viewHolder[i].mtext2 = (TextView)view.findViewById(R.id.mtext2);
            viewHolder[i].mtext3 = (TextView)view.findViewById(R.id.mtext3);
            view.setTag(viewHolder[i]);
        }

        viewHolder[i] = (ViewHolder)view.getTag();
        viewHolder[i].mBtn.setTag(R.id.btn,i);
        //viewHolder[i].mlike.setTag(R.id.like,i);//添加此代码
        viewHolder[i].mtext1.setTag(R.id.text1,i);//添加此代码
        viewHolder[i].mtext2.setTag(R.id.text2,i);//添加此代码
        viewHolder[i].mtext3.setTag(R.id.text3,i);//添加此代码
        viewHolder[i].mtext1.setText(data.get(i).get("email").toString());
        viewHolder[i].mtext2.setText(data.get(i).get("message").toString());
        viewHolder[i].mtext3.setText(data.get(i).get("time").toString());
        viewHolder[i].mBtn.setBackgroundResource(R.drawable.emptyheart);
        viewHolder[i].mBtn.setTag(R.drawable.emptyheart);










        viewHolder[i].mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int t = (int)view.getTag(R.id.btn);
                Integer resource = (Integer) viewHolder[t].mBtn.getTag();
                if(resource != R.drawable.heart) {
                    viewHolder[t].mBtn.setBackgroundResource(R.drawable.heart);
                    viewHolder[t].mBtn.setTag(R.drawable.heart);
                }else{
                    viewHolder[t].mBtn.setBackgroundResource(R.drawable.emptyheart);
                    viewHolder[t].mBtn.setTag(R.drawable.emptyheart);
                }

            }
        });
        return view;
    }


    public class ViewHolder{
        ImageButton mBtn;
        //ImageView mlike;
        TextView mtext1;
        TextView mtext2;
        TextView mtext3;
    }

}