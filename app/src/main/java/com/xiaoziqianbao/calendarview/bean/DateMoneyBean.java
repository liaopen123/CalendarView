package com.xiaoziqianbao.calendarview.bean;

/**
 * Created by liaopenghui on 2017/4/13.
 */

public class DateMoneyBean {
    private String  Data;
    private String money;

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "DateMoneyBean{" +
                "Data='" + Data + '\'' +
                ", money='" + money + '\'' +
                '}';
    }
}
