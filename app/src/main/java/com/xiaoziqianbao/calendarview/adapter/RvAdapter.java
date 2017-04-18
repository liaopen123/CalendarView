package com.xiaoziqianbao.calendarview.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiaoziqianbao.calendarview.R;
import com.xiaoziqianbao.calendarview.bean.MonthBean;
import com.xiaoziqianbao.calendarview.utils.DateUtils;

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
    private final MonthBean monthBean;
    private final int currentDayOfMonth;//今天几号



    private ItemOnclickListener itemClickListener;


    /**
     *  @param context context
     * @param year  年
     * @param month 月
     * @param monthBean
     */
    public RvAdapter(Context context, int year, int month, MonthBean monthBean) {
        this.year = year;
        this.month = month;
        this.context= context;
        this.monthBean = monthBean;
        //根据传入的年份和月份，获取当前月份的日历分布
      dayOfMonth = DateUtils.getDayOfMonth(year, month);
        currentDayOfMonth = DateUtils.getCurrentDayOfMonth();
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




            int i = dayOfMonth.indexOf(1);
            int i1 = DateUtils.getDaysOfMonth(year, month)+i+1;//这个月有多少天
            if(position<i){
                //一之前的位置不展示

            }else if(position>i1){

            }else{

                Integer integer = dayOfMonth.get(position);
             String content =   monthBean.getDates().contains(integer)? integer+"H":integer+"";

                holder.tv_name.setText(content);
               if(isSameDay(year,month,integer)){
                   //是今天
                   holder.tv_name.setTextColor(Color.RED);
               }else {
                   holder.tv_name.setTextColor(Color.BLACK);
               }
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


        public boolean isSameDay(int year,int month,int day){
            if(year==DateUtils.getYear()&&month==DateUtils.getMonth()&&day==DateUtils.getCurrentDayOfMonth()){
                return true;
            }
            return false;
        }
}
