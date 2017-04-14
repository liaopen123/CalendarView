package com.xiaoziqianbao.calendarview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiaoziqianbao.calendarview.R;
import com.xiaoziqianbao.calendarview.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liaopenghui on 2017/4/13.
 */

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.MyViewHolder> {
    private final Context context;
//    private final int[][] dayOfMonthFormat;
    private final List<Integer> dayOfMonth;
    private final int year;
    private final int month;
    private ArrayList<String> lists = null;

    private boolean start=false;


    private ItemOnclickListener itemClickListener;

//    public RvAdapter(Context context, ArrayList<String> lists) {
//        this.context= context;
//        this.lists = lists;
//    }
//    public RvAdapter(Context context) {
//        this.context= context;
//
//    }

//    public RvAdapter(CalendarActivity context, int[][] dayOfMonthFormat) {
//        this.context= context;
//        this.dayOfMonthFormat = dayOfMonthFormat;
//    }

//    public RvAdapter(CalendarActivity context, List<Integer> dayOfMonth) {
//        this.context= context;
//        this.dayOfMonth = dayOfMonth;
//    }

    /**
     *
     * @param context context
     * @param year  年
     * @param month 月
     */
    public RvAdapter(Context context, int year, int month) {
        this.year = year;
        this.month = month;
        this.context= context;
      dayOfMonth = DateUtils.getDayOfMonth(year, month);
    }

    public void setOnItemClickListener(ItemOnclickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_rv, parent, false));
            return  holder;

    }



    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        if(holder instanceof MyViewHolder) {



            int i = dayOfMonth.indexOf(1);
            int i1 = DateUtils.getDaysOfMonth(year, month)+i+1;//这个月有多少天
            if(position<i){
                //一之前的位置不展示

            }else if(position>i1){

            }else{
                holder.tv_name.setText(dayOfMonth.get(position)+"");
            }
//            dayOfMonth
//            ((MyViewHolder)holder).tv_name.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (null != itemClickListener) {
//                        itemClickListener.setonTextViewClickListener(view, position);
//                    }
//                }
//            });
        }
    }
    /*总天数：1号在第周几+1+总天数*/
    @Override
    public int getItemCount() {
        return dayOfMonth.indexOf(1)+DateUtils.getDaysOfMonth(year, month);
    }



    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView tv_name;

        public MyViewHolder(View itemView) {
            super(itemView);
            //为每个item设置点击事件
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(null!=itemClickListener){
                        itemClickListener.setonClickListener(year+"年"+month+"月");
                    }
                }
            });
            tv_name =  (TextView)itemView.findViewById(R.id.date);
        }
    }


    public interface ItemOnclickListener{
        public void setonClickListener(String date);
    }



}
