package com.xiaoziqianbao.calendarview;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.xiaoziqianbao.calendarview.adapter.CalendarViewAdapter1;
import com.xiaoziqianbao.calendarview.adapter.GalleryAdapter;
import com.xiaoziqianbao.calendarview.utils.DateUtils;

import butterknife.ButterKnife;


public class CalendarActivity extends AppCompatActivity {

    private RecyclerView rv;
    private StaggeredGridLayoutManager mLayoutManager;
    private int[][] dayOfMonthFormat;
    private LinearLayout ll_container;
    private  View[] views;
    private CalendarViewAdapter1 adapter;
    private ViewPager vp;
    private String TAG ="CalendarActivity";
    String[] mDateTitles = new String[15];
    private RecyclerView rv_vertical;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        ButterKnife.inject(this);
//        rv = ((CalendarViewNew) findViewById(R.id.rv));
//        ll_container = ((LinearLayout) findViewById(R.id.ll_container));
//        mLayoutManager = new StaggeredGridLayoutManager(7, StaggeredGridLayoutManager.VERTICAL);
//        rv.setLayoutManager(mLayoutManager);
////        dayOfMonthFormat = DateUtils.getDayOfMonthFormat(DateUtils.getYear(), DateUtils.getMonth());
////        rv.setAdapter(new RvAdapter(this,dayOfMonthFormat));
//
////        List<Integer> dayOfMonth = DateUtils.getDayOfMonth(DateUtils.getYear(), DateUtils.getMonth()-2);
////        rv.setAdapter(new RvAdapter(this,dayOfMonth));
//
//        rv.setAdapter(new RvAdapter(this, 2016,DateUtils.getMonth()-2));
//        rv.setData(this,2016, DateUtils.getMonth()-2);
        vp = ((ViewPager) findViewById(R.id.vp));
        rv = ((RecyclerView) findViewById(R.id.rv));
        //设置布局管理器
        initDatas();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv.setLayoutManager(linearLayoutManager);
        rv.addItemDecoration(new SpacesItemDecoration(2));
        //设置适配器
        GalleryAdapter mAdapter = new GalleryAdapter(this, mDateTitles);
        rv.setAdapter(mAdapter);
        mAdapter.setOnClikListener(new GalleryAdapter.OnItemClickListener() {
            @Override
            public void onitemClik(int Position) {
                vp.setCurrentItem(Position);
            }
        });






        adapter = new CalendarViewAdapter1(this, DateUtils.getYear(),DateUtils.getMonth());
        vp.setAdapter(adapter);
        vp.setCurrentItem(3);
        vp.setOffscreenPageLimit(1);
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG,"position"+position+"被选定");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



        //竖向的列表
        rv_vertical = ((RecyclerView) findViewById(R.id.rv_vertical));
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
        linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        rv_vertical.setLayoutManager(linearLayoutManager1);
        rv_vertical.addItemDecoration(new SpacesItemDecoration(2));
        //设置适配器
        GalleryAdapter mAdapter1 = new GalleryAdapter(this, mDateTitles);
        rv_vertical.setAdapter(mAdapter1);
        mAdapter1.setOnClikListener(new GalleryAdapter.OnItemClickListener() {
            @Override
            public void onitemClik(int Position) {
                vp.setCurrentItem(Position);
            }
        });

    }

    private void initDatas()
    {
     for(int i = 0;i<15;i++){
         mDateTitles[i] = DateUtils.getMonth()-3+i+"月";
     }
    }

}
