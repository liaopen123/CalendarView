package com.xiaoziqianbao.calendarview.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiaoziqianbao.calendarview.R;

import java.util.ArrayList;

/**
 * Created by Pony on 2017/3/19.
 */

public class GalleryAdapter extends RecyclerView.Adapter {

    private final LayoutInflater mInflater;
    private final String[] mDateTitles;
    Context context;
    ArrayList<Integer> mDatas;
    private OnItemClickListener onItemClickListener;
//    public GalleryAdapter(Context context, ArrayList<Integer> mDatas) {
//        this.context = context;
//        mInflater = LayoutInflater.from(context);
//        this.mDatas =mDatas;
//
//    }

    public GalleryAdapter(Context context, String[] mDateTitles) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
             this.mDateTitles = mDateTitles;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_horizontal,
                parent, false);
        ViewHolder1 viewHolder = new ViewHolder1(view);

        viewHolder.mImg = (TextView) view
                .findViewById(R.id.iv);
        LinearLayout ll = (LinearLayout) view
                .findViewById(R.id.ll);

        ViewGroup.LayoutParams linearParams = ll
                .getLayoutParams();

        DisplayMetrics dm = new DisplayMetrics();
        dm = context.getResources().getDisplayMetrics();


        linearParams.width = dm.widthPixels /4;
        ll.setLayoutParams(linearParams);
    viewHolder.linearLayout = ll;
        viewHolder.linearLayout.setLayoutParams(linearParams);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder1 viewHolder1 = (ViewHolder1) holder;
        viewHolder1.mImg.setText(mDateTitles[position]+"月");
        viewHolder1.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) {
                    onItemClickListener.onitemClik(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDateTitles.length;
    }





    public static class ViewHolder1  extends RecyclerView.ViewHolder
    {
        public ViewHolder1(View arg0)
        {
            super(arg0);
        }

        TextView mImg;
        LinearLayout linearLayout;
    }


    public interface  OnItemClickListener{
        void onitemClik(int  Position);
    }

    public void  setOnClikListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
}
