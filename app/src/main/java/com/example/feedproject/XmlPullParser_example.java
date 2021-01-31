package com.example.feedproject;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class XmlPullParser_example extends RecyclerView.Adapter<XmlPullParser_example.MyViewHolder> {

    private static final String TAG = "XmlPullParser_example";
    private ArrayList<XmlPullParser_fsk> mList;
    private LayoutInflater mInflate;
    private Context mContext;

    public void XmlPullParser_example(Context context, ArrayList<XmlPullParser_fsk> itmes) {
        this.mList = itmes;
        this.mInflate = LayoutInflater.from(context);
        this.mContext = context;
    }

    @NonNull
    @Override
    public XmlPullParser_example.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflate.inflate(R.layout.activity_xml_pull_parser_example, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //binding
        holder.food_cd.setText(mList.get(position).food_cd);
        holder.region_name.setText(mList.get(position).region_name);
        holder.month_name.setText(mList.get(position).month_name);
        holder.desk_kor.setText(mList.get(position).desk_kor);

        //Click event
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    //ViewHolder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView food_cd;
        public TextView region_name;
        public TextView month_name;
        public TextView desk_kor;

        public MyViewHolder(View itemView) {
            super(itemView);

            food_cd = itemView.findViewById(R.id.tv_food_cd2);
            region_name = itemView.findViewById(R.id.tv_region_name2);
            month_name = itemView.findViewById(R.id.tv_month_name2);
            desk_kor = itemView.findViewById(R.id.tv_desk_kor2);

        }
    }
}