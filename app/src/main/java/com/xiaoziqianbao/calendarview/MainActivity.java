package com.xiaoziqianbao.calendarview;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xiaoziqianbao.calendarview.adapter.CalendarViewAdapter;

public class MainActivity extends AppCompatActivity {

    private ViewPager vp;
    private CalendarViewAdapter<View> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vp = ((ViewPager) findViewById(R.id.vp));
        View[] views = new View[3];

        View view1 = View.inflate(this, R.layout.content_calendar, null);
//        View view2 = View.inflate(this, R.layout.content_calendar, null);
//        View view3 = View.inflate(this, R.layout.content_calendar, null);

        views[0] =view1;
//        views[1] =view2;
//        views[2] =view3;
        adapter = new CalendarViewAdapter<>(views);
        vp.setAdapter(adapter);


    }
}
