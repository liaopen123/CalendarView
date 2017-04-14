package com.xiaoziqianbao.calendarview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewConfiguration;

import com.xiaoziqianbao.calendarview.utils.DateUtils;

/**
 * Created by liaopenghui on 2017/4/13.
 */

public class CalendarView extends View {

    private static final int TOTAL_COL = 7; // 7列
    private static final int TOTAL_ROW = 6; // 6行
    private int mViewWidth;//view的宽度
    private int mViewHeight;//view的高度
    private int mCellSpace;//单元格的宽高
    private Paint mTextPaint;
    private Paint mCirclePaint;
    private  Context context;
    private int touchSlop;
    private int[][] dayOfMonthFormat;
    private String TAG = "CalendarView";

    public CalendarView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public CalendarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public CalendarView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();

    }

    private void initData() {
       dayOfMonthFormat = DateUtils.getDayOfMonthFormat(DateUtils.getYear(), DateUtils.getMonth());
    }

    private void init() {
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setColor(Color.parseColor("#F24949")); // 红色圆形
        touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        initData();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;
        mCellSpace = Math.min(mViewHeight / TOTAL_ROW, mViewWidth / TOTAL_COL);
//       //设置文字大小
        mTextPaint.setTextSize(mCellSpace / 3);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        // 在wrap_content的情况下默认长度为200dp
        int minSize = widthSpecSize;
        // wrap_content的specMode是AT_MOST模式，这种情况下宽/高等同于specSize
        // 查表得这种情况下specSize等同于parentSize，也就是父容器当前剩余的大小
        // 在wrap_content的情况下如果特殊处理，效果等同martch_parent
        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(minSize, minSize);
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(minSize, heightSpecSize);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize, minSize);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画网格
        for(int i=0;i<=TOTAL_ROW;i++){
            canvas.drawLine(0,mCellSpace*i , mViewWidth, mCellSpace*i, mTextPaint);
        }
        for(int i=0;i<=TOTAL_COL;i++){
            canvas.drawLine(mCellSpace*i, 0,mCellSpace*i,   mCellSpace*TOTAL_ROW, mTextPaint);
        }

        int row = 0;
        //写日期
        for (int i = 0; i < dayOfMonthFormat.length; i++) {
            int col = 0; //行位置

            for (int j = 0; j < dayOfMonthFormat[i].length; j++) {
                //循环遍历数组中的每个元素
                int dayDate = dayOfMonthFormat[i][j];
               Log.d(TAG,dayDate+"");
                //横向写文字
                canvas.drawText(dayDate+"",col*mCellSpace+mCellSpace*0.5f,   row*mCellSpace+mCellSpace*0.5f,mTextPaint);

                //写圆点
//                canvas.drawCircle((float) (mCellSpace * (i + 0.5)), (float) ((j + 0.5) * mCellSpace), mCellSpace / 3, mCirclePaint);
                col++;
            }
            //输出空格
            row++;
        }
    }
}
