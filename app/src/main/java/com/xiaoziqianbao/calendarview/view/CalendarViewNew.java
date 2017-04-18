package com.xiaoziqianbao.calendarview.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

import com.xiaoziqianbao.calendarview.adapter.RvAdapter;
import com.xiaoziqianbao.calendarview.bean.MonthBean;

/**
 * Created by liaopenghui on 2017/4/14.
 */

public class CalendarViewNew extends RecyclerView {
    private StaggeredGridLayoutManager mLayoutManager;
    private OnItemClickListener onclickListener;

    public CalendarViewNew(Context context) {
        super(context);
        init();
    }

    public CalendarViewNew(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CalendarViewNew(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        mLayoutManager = new StaggeredGridLayoutManager(7, StaggeredGridLayoutManager.VERTICAL);
        setLayoutManager(mLayoutManager);
    }
    public void setData(Context context, int year, int month, MonthBean monthBean){
        RvAdapter rvAdapter = new RvAdapter(context, year, month,monthBean);
        setAdapter(rvAdapter);
        rvAdapter.setOnItemClickListener(new RvAdapter.ItemOnclickListener() {
            @Override
            public void setonClickListener(String date) {
                if (onclickListener != null) {
                    onclickListener.onClick(date);
                }
            }


        });
//        rvAdapter.notify();

    }

    public void setOnclickListener(OnItemClickListener onclickListener){
        this.onclickListener = onclickListener;
    }


    public interface  OnItemClickListener{
        void onClick(String date);
    }
}
