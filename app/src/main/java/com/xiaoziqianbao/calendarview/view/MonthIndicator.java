package com.xiaoziqianbao.calendarview.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.xiaoziqianbao.calendarview.SpacesItemDecoration;
import com.xiaoziqianbao.calendarview.adapter.GalleryAdapter;

/**
 * Created by liaopenghui on 2017/4/18.
 * 抽取顶部月份显示的recyclerview
 */

public class MonthIndicator extends RecyclerView {
    private  Context context;
    private OnItemClickListener onItemClickListener;

    public MonthIndicator(Context context) {
        super(context);
        this.context = context;
        init();
    }



    public MonthIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public MonthIndicator(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        init();
    }


    private void init() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        setLayoutManager(linearLayoutManager);
        addItemDecoration(new SpacesItemDecoration(2));
    }


    public void intDates(String[] months){
        GalleryAdapter mAdapter = new GalleryAdapter(context, months);
        setAdapter(mAdapter);
        mAdapter.setOnClikListener(new GalleryAdapter.OnItemClickListener() {
            @Override
            public void onitemClik(int position) {
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(position);
                }

            }
        });
    }






    public void setOnItemClickListener( OnItemClickListener  onItemClickListener ) {
        this.onItemClickListener = onItemClickListener;
    }


    public  interface OnItemClickListener{
        void onClick(int Position);
    }
}
