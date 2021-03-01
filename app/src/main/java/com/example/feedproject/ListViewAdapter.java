package com.example.feedproject;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    ImageView imageView;
    Bitmap bitmap;

    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<list_item> list;

    public ListViewAdapter(Context context, ArrayList<list_item> listViewItemList) {
        mContext = context;
        list = listViewItemList;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View  getView(final int position, View convertView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.activity_list_item, null);

        imageView = (ImageView) view.findViewById(R.id.imageView1);
        TextView titleTextView = (TextView) view.findViewById(R.id.textView1) ;
        TextView descTextView = (TextView) view.findViewById(R.id.textView2) ;

        Thread mThread = new Thread() {
            @Override
            public void run() {
                try {
                    URL url = new URL(list.get(position).getImage());

                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    conn.setDoInput(true);
                    conn.connect();

                    InputStream is = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        mThread.start();

        try {
            mThread.join();
            imageView.setImageBitmap(bitmap);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        titleTextView.setText(list.get(position).getName());
        descTextView.setText(list.get(position).getDesc());

        return view;
    }

}
