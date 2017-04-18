package com.xiaoziqianbao.calendarview;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.xiaoziqianbao.calendarview.adapter.CalendarViewAdapter1;
import com.xiaoziqianbao.calendarview.adapter.GalleryAdapter;
import com.xiaoziqianbao.calendarview.bean.MonthBean;
import com.xiaoziqianbao.calendarview.view.MonthIndicator;

import java.util.ArrayList;

import butterknife.ButterKnife;


public class CalendarActivity extends AppCompatActivity {

    private MonthIndicator monthIndicator;
    private CalendarViewAdapter1 adapter;
    private ViewPager vp;
    private String TAG = "CalendarActivity";
    String[] mDateTitles = new String[8];
    private ArrayList<MonthBean> monthData;
    private int mPosition;
    private TextView tv_month;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        ButterKnife.inject(this);

        vp = ((ViewPager) findViewById(R.id.vp));
        tv_month = ((TextView) findViewById(R.id.tv_month));
        monthIndicator = ((MonthIndicator) findViewById(R.id.rv));

        monthIndicator.setOnItemClickListener(new MonthIndicator.OnItemClickListener() {
            @Override
            public void onClick(int Position) {
                vp.setCurrentItem(Position);
            }
        });
        //设置布局管理器
        initDatas();

        //设置适配器


        //从1月开始
        adapter = new CalendarViewAdapter1(this, 2017, 1, monthData);
        vp.setAdapter(adapter);
        vp.setCurrentItem(3);
        tv_month.setText("2017年4月");
        vp.setOffscreenPageLimit(1);
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, "position" + position + "被选定");
                //用来控制下方列表的切换回调     日历内部的  日历自己处理
                int[] yearAndMonth = getYearAndMonth(2017, 1, position);
                tv_month.setText(yearAndMonth[0]+"年"+yearAndMonth[1]+"月");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        //竖向的列表
        RecyclerView rv_vertical = ((RecyclerView) findViewById(R.id.rv_vertical));
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

    private void initDatas() {
        monthData = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            ArrayList<Integer> days = new ArrayList<>();
            days.add(i + 2);
            MonthBean monthBean = new MonthBean(days, (long) (i + 1));
            monthData.add(monthBean);
        }


        //筛选数据 到月份上去
        for (int i = 0; i < monthData.size(); i++) {
            mDateTitles[i] = monthData.get(i).getDate() + "月";
        }
        //给顶部选择器赋值
        monthIndicator.intDates(mDateTitles);
    }





    private int[] getYearAndMonth(int baseYear,int BaseMonth,int position) {
        int date[] = new int[2];
        Log.d(TAG,"得到的position："+position);
        date[0] = baseYear;
        date[1] = BaseMonth +position;;
        if(date[1]==13){
            //记录下出现递增的第一个月
            mPosition = position;
            //月份开始大于12
            date[0]=baseYear+1;
            date[1] = 1;

        }else if(date[1]>13){
            date[0]=baseYear+1;
            date[1] = position-mPosition+1;//从1月份加 增加的几个月
        }
        Log.d(TAG,date[0]+"年"+date[1]+"月");
        return date;
    }


}
