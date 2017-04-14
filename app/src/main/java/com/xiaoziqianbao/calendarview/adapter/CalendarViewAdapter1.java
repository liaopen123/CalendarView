package com.xiaoziqianbao.calendarview.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.xiaoziqianbao.calendarview.view.CalendarViewNew;

public class CalendarViewAdapter1<V extends View> extends PagerAdapter {
	private final int year;
	private final int month;
	private SparseArray<CalendarViewNew> mViews;
	private Context mContext;
	private TypedArray mArray;
	private int mMonthCount;
	private String TAG ="CalendarViewAdapter1";
	private int mPosition;

	public CalendarViewAdapter1(Context context,int year,int month) {
		mContext = context;
		mViews = new SparseArray<>();
		mMonthCount = 15;
		this.year = year;
		this.month = month;
		int monthCount = 0;
	}

	@Override
	public int getCount() {
		return mMonthCount;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		if (mViews.get(position) == null) {
			int date[] = getYearAndMonth(position);
			CalendarViewNew monthView = new CalendarViewNew(mContext);
			monthView.setId(position);
			monthView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
			monthView.invalidate();
//			monthView.setOnDateClickListener(mMonthCalendarView);
			monthView.setData(mContext,date[0],date[1]);
			mViews.put(position, monthView);
			monthView.setOnclickListener(new CalendarViewNew.OnItemClickListener() {
				@Override
				public void onClick(String date) {
					Toast.makeText(mContext, "点击了是"+date, Toast.LENGTH_SHORT).show();
				}
			});
		}
		container.addView(mViews.get(position));
		return mViews.get(position);
	}

	private int[] getYearAndMonth(int position) {
		int date[] = new int[2];
		Log.d(TAG,"得到的position："+position);
		date[0] = year;
		date[1] = month +position;;
		if(date[1]==13){
			//记录下出现递增的第一个月
			mPosition = position;
			//月份开始大于12
			date[0]=year+1;
			date[1] = 1;

		}else if(date[1]>13){
			date[0]=year+1;
			date[1] = position-mPosition+1;//从1月份加 增加的几个月
		}
		Log.d(TAG,date[0]+"年"+date[1]+"月");
		return date;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}



	public int getMonthCount() {
		return mMonthCount;
	}
}
