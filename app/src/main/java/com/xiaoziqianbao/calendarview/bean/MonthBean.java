package com.xiaoziqianbao.calendarview.bean;

import java.util.ArrayList;

/**
 * Created by liaopenghui on 2017/4/18.
 */

public class MonthBean {

    private ArrayList<Integer> dates;
    private Long Date;//日期

    public MonthBean(ArrayList<Integer> dates, Long date) {
        this.dates = dates;
        Date = date;
    }
    public MonthBean() {

    }

    public ArrayList<Integer> getDates() {
        return dates;
    }

    public void setDates(ArrayList<Integer> dates) {
        this.dates = dates;
    }

    public Long getDate() {
        return Date;
    }

    public void setDate(Long date) {
        Date = date;
    }
}
