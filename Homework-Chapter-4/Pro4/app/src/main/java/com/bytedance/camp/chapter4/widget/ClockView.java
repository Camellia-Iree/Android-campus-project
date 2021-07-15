package com.bytedance.camp.chapter4.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class ClockView extends View {

    private static final int FULL_CIRCLE_DEGREE = 360;
    private static final int UNIT_DEGREE = 6;

    private static final float UNIT_LINE_WIDTH = 8; // 刻度线的宽度
    private static final int HIGHLIGHT_UNIT_ALPHA = 0xFF;
    private static final int NORMAL_UNIT_ALPHA = 0x80;

    private static final float HOUR_NEEDLE_LENGTH_RATIO = 0.4f; // 时针长度相对表盘半径的比例
    private static final float MINUTE_NEEDLE_LENGTH_RATIO = 0.6f; // 分针长度相对表盘半径的比例
    private static final float SECOND_NEEDLE_LENGTH_RATIO = 0.8f; // 秒针长度相对表盘半径的比例
    private static final float HOUR_NEEDLE_WIDTH = 12; // 时针的宽度
    private static final float MINUTE_NEEDLE_WIDTH = 8; // 分针的宽度
    private static final float SECOND_NEEDLE_WIDTH = 4; // 秒针的宽度

    private Paint.FontMetrics fontMetrics = new Paint.FontMetrics();

    private Calendar calendar = Calendar.getInstance();

    private float radius = 0; // 表盘半径
    private float centerX = 0; // 表盘圆心X坐标
    private float centerY = 0; // 表盘圆心Y坐标

    private List<RectF> unitLinePositions = new ArrayList<>();
    private Paint unitPaint = new Paint();
    private Paint timePaint = new Paint();
    private Paint needlePaint = new Paint();
    private Paint numberPaint = new Paint();
    private Paint circlePaint = new Paint();

    private int AP;

    private Handler handler = new Handler(){
      public void handleMessage(Message msg){
          invalidate();
          // Toast.makeText(getContext(), "-1s", Toast.LENGTH_SHORT).show();
      }
    };


    public ClockView(Context context) {
        super(context);
        init();
    }

    public ClockView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ClockView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        unitPaint.setAntiAlias(true);
        unitPaint.setColor(Color.WHITE);
        unitPaint.setStrokeWidth(UNIT_LINE_WIDTH);
        unitPaint.setStrokeCap(Paint.Cap.ROUND);
        unitPaint.setStyle(Paint.Style.STROKE);

        // TODO 设置绘制时、分、秒针的画笔: needlePaint
        needlePaint.setAntiAlias(true);
        needlePaint.setColor(Color.WHITE);
        needlePaint.setStyle(Paint.Style.FILL);

        // TODO 设置绘制时间数字的画笔: numberPaint
        numberPaint.setAntiAlias(true);
        numberPaint.setColor(Color.WHITE);
        numberPaint.setStyle(Paint.Style.FILL);
        numberPaint.setTextSize(50);

        circlePaint.setAntiAlias(true);     //抗锯齿
        circlePaint.setColor(Color.WHITE);

        timePaint.setAntiAlias(true);
        timePaint.setTextSize(100);
        timePaint.setColor(Color.WHITE);
        timePaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        configWhenLayoutChanged();
    }

    private void configWhenLayoutChanged() {
        float newRadius = Math.min(getWidth(), getHeight()) / 2f;
        if (newRadius == radius) {
            return;
        }
        radius = newRadius;
        centerX = getWidth() / 2f;
        centerY = getHeight() / 2f;

        // 当视图的宽高确定后就可以提前计算表盘的刻度线的起止坐标了
        for (int degree = 0; degree < FULL_CIRCLE_DEGREE; degree += UNIT_DEGREE) {
            double radians = Math.toRadians(degree);
            float startX = (float) (centerX + (radius * (1 - 0.05f)) * Math.cos(radians));
            float startY = (float) (centerY + (radius * (1 - 0.05f)) * Math.sin(radians));
            // float startY = (float) (centerX + (radius * (1 - 0.05f)) * Math.sin(radians));
            // 原句，是不是centerX应该改为centerY？
            float stopX = (float) (centerX + radius * Math.cos(radians));
            float stopY = (float) (centerY + radius * Math.sin(radians));
            unitLinePositions.add(new RectF(startX, startY, stopX, stopY));
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawUnit(canvas);
        drawTimeNumbers(canvas);
        drawTimeNeedles(canvas);
        // TODO 实现时间的转动，每一秒刷新一次
        handler.sendEmptyMessage(1000);
        // Toast.makeText(getContext(),"+1s",Toast.LENGTH_SHORT).show();
    }

    // 绘制表盘上的刻度
    private void drawUnit(Canvas canvas) {
        for (int i = 0; i < unitLinePositions.size(); i++) {
            if (i % 5 == 0) {
                unitPaint.setAlpha(HIGHLIGHT_UNIT_ALPHA);
            } else {
                unitPaint.setAlpha(NORMAL_UNIT_ALPHA);
            }
            RectF linePosition = unitLinePositions.get(i);
            canvas.drawLine(linePosition.left, linePosition.top, linePosition.right, linePosition.bottom, unitPaint);
        }
    }

    private void drawTimeNeedles(Canvas canvas) {
        Time time = getCurrentTime();
        int hour = time.getHours();
        int minute = time.getMinutes();
        int second = time.getSeconds();
        // TODO 根据当前时间，绘制时针、分针、秒针
        // 计算角度
        double radians_H = (double)(hour * 30 + minute / 2);
        double radians_M = (double)(minute * 6);
        double radians_S = (double)(second * 6);

        radians_H = (radians_H + 270) % 360;
        radians_M = (radians_M + 270) % 360;
        radians_S = (radians_S + 270) % 360;

        radians_H = Math.toRadians(radians_H);
        radians_M = Math.toRadians(radians_M);
        radians_S = Math.toRadians(radians_S);

        float startX, startY, stopX, stopY;

        // 时针：
        startX = (float) (centerX);
        startY = (float) (centerY);
        stopX = (float) (centerX + HOUR_NEEDLE_LENGTH_RATIO * radius * Math.cos(radians_H));
        stopY = (float) (centerY + HOUR_NEEDLE_LENGTH_RATIO * radius * Math.sin(radians_H));
        needlePaint.setStrokeWidth(HOUR_NEEDLE_WIDTH);
        canvas.drawLine(startX, startY, stopX, stopY, needlePaint);

        // 分针：
        startX = (float) (centerX);
        startY = (float) (centerY);
        stopX = (float) (centerX + MINUTE_NEEDLE_LENGTH_RATIO * radius * Math.cos(radians_M));
        stopY = (float) (centerY + MINUTE_NEEDLE_LENGTH_RATIO * radius * Math.sin(radians_M));
        needlePaint.setStrokeWidth(MINUTE_NEEDLE_WIDTH);
        canvas.drawLine(startX, startY, stopX, stopY, needlePaint);

        // 秒针：
        startX = (float) (centerX);
        startY = (float) (centerY);
        stopX = (float) (centerX + SECOND_NEEDLE_LENGTH_RATIO * radius * Math.cos(radians_S));
        stopY = (float) (centerY + SECOND_NEEDLE_LENGTH_RATIO * radius * Math.sin(radians_S));
        needlePaint.setStrokeWidth(SECOND_NEEDLE_WIDTH);
        canvas.drawLine(startX, startY, stopX, stopY, needlePaint);

        // 正中间的圆心
        canvas.drawCircle(centerX, centerY,10, circlePaint);

        // 下面的文字
        String a, b, c;
        if (hour < 10) a = "0" + hour;
        else a = "" + hour;
        if (minute < 10) b = "0" + minute;
        else b = "" + minute;
        if (second < 10) c = "0" + second;
        else c = "" + second;

        String output = a + " : " + b + " : " + c;
        canvas.drawText(output, (float)(centerX - 230), (float)(centerY + radius * 1.6), timePaint);

        /**
         * 思路：
         * 1、以时针为例，计算从0点（12点）到当前时间，时针需要转动的角度
         * 2、根据转动角度、时针长度和圆心坐标计算出时针终点坐标（起始点为圆心）
         * 3、从圆心到终点画一条线，此为时针
         * 注1：计算时针转动角度时要把时和分都得考虑进去
         * 注2：计算坐标时需要用到正余弦计算，请用Math.sin()和Math.cos()方法
         * 注3：Math.sin()和Math.cos()方法计算时使用不是角度而是弧度，所以需要先把角度转换成弧度，
         *     可以使用Math.toRadians()方法转换，例如Math.toRadians(180) = 3.1415926...(PI)
         * 注4：Android视图坐标系的0度方向是从圆心指向表盘3点方向，指向表盘的0点时是-90度或270度方向，要注意角度的转换
         */
        // int hourDegree = 180;
        // float endX = (float) (centerX + radius * HOUR_NEEDLE_LENGTH_RATIO * Math.cos(Math.toRadians(hourDegree)))
    }

    private void drawTimeNumbers(Canvas canvas) {
        // TODO 绘制表盘时间数字（可选）
        // Paint使用numberPaint
        int i;
        float tem = radius * (1 - 0.17f);
        for(i = 0; i < 12; i++){
            double radians = (i * 30 + 270) % 360;
            radians = Math.toRadians(radians);
            float X = (float)(centerX + tem * Math.cos(radians));
            float Y = (float)(centerY + tem * Math.sin(radians));
            Y = Y + 18;  // 打印中心在左下导致的偏移
            X = X - 12;
            canvas.drawText("" + i, X, Y, numberPaint);
        }
    }

    // 获取当前的时间：时、分、秒
    private Time getCurrentTime() {
        calendar.setTimeInMillis(System.currentTimeMillis());
        return new Time(
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                calendar.get(Calendar.SECOND)
                );
    }
}
