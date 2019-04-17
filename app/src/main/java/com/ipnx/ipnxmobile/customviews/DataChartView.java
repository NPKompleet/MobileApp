package com.ipnx.ipnxmobile.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Interpolator;

import com.ipnx.ipnxmobile.R;
import com.nineoldandroids.animation.ObjectAnimator;

import static java.lang.Math.min;

public class DataChartView extends View {

    private int totalDataColor, usedDataColor, centerColor;
    private float totalDataValue, usedDataValue;
    private Paint totalDataPaint, usedDataPaint, centerPaint;
    RectF rect = new RectF();

    ObjectAnimator animator;
    private Interpolator interpolator;

    public DataChartView(Context context) {
        super(context);
    }

    public DataChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DataChartView);
        totalDataColor = typedArray.getColor(R.styleable.DataChartView_totalDataColor, Color.BLUE);
        usedDataColor = typedArray.getColor(R.styleable.DataChartView_usedDataColor, Color.RED);
        centerColor = typedArray.getColor(R.styleable.DataChartView_centerColor, Color.GREEN);

        totalDataValue = typedArray.getFloat(R.styleable.DataChartView_totalDataValue, 500.0f);
        usedDataValue = typedArray.getFloat(R.styleable.DataChartView_usedDataValue, 100.0f);

        typedArray.recycle();

        totalDataPaint = new Paint();
        totalDataPaint.setColor(totalDataColor);
        totalDataPaint.setStyle(Paint.Style.FILL);
        totalDataPaint.setAntiAlias(true);

        usedDataPaint = new Paint();
        usedDataPaint.setColor(usedDataColor);
        usedDataPaint.setStyle(Paint.Style.FILL);
        usedDataPaint.setAntiAlias(true);

        centerPaint = new Paint();
        centerPaint.setColor(centerColor);
        centerPaint.setStyle(Paint.Style.FILL);
        centerPaint.setAntiAlias(true);
        
        
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int height = getHeight();
        int width = getWidth();

        float radius = min(height, width) / 2.0f ;
        float centerY = height - radius;

        rect.left = 0f;
        rect.top = 0f;
        rect.right = width;
        rect.bottom = height;

        canvas.drawCircle(radius, centerY, radius - dpToPx(8), totalDataPaint);
        canvas.drawCircle(radius, centerY, radius/2 + dpToPx(8), centerPaint);
        canvas.drawArc(rect, 180.0f, usedDataValue/totalDataValue * 360, true, usedDataPaint);
//        usedDataPaint.setColor(Color.BLUE);
//        canvas.drawArc(rect, 255.0f, 75.0f, true, usedDataPaint);
        canvas.drawCircle(radius, centerY, radius/2, centerPaint);
    }

    private int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public void setInterpolator(Interpolator i){
        interpolator=i;
    }

    public void moveTo(float totalValue, float value) {
        if(animator!=null&&animator.isRunning()){
            animator.cancel();
        }
        this.totalDataValue = totalValue;
        this.usedDataValue = value;
        move();
    }

    private void move() {
        animator = ObjectAnimator.ofFloat(this, "usedDataValue", 0.0f, usedDataValue);
//        int duration=(int)((Math.abs(value-currentPoint)/maxValue)*4000);
        animator.setDuration(1500);
        if(interpolator!=null){
            animator.setInterpolator(interpolator);
        }
        animator.start();
    }

    public float getUsedDataValue() {
        return usedDataValue;
    }

    public void setUsedDataValue(float usedDataValue) {
        this.usedDataValue = usedDataValue;
        invalidate();
    }
}
