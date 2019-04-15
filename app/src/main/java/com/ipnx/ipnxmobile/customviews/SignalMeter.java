package com.ipnx.ipnxmobile.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Interpolator;

import com.ipnx.ipnxmobile.R;
import com.nineoldandroids.animation.ObjectAnimator;

/**
 * This SignalMeter view was adapted from the MeterView inn this same project by Philip Okonkwo.
 * It was adapted to account for the negative meter values of wifi signal strength in this project.
 * I just wrote a new interface for it. Most of the functions are basically the same.
 */
public class SignalMeter extends View {
    // ===========================================================
    // Constants
    // ===========================================================
    private  static final int DEFAULT_SIZE = 300;

    // ===========================================================
    // Fields
    // ===========================================================

    private Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint markerTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint arcPaint = new Paint();
    private float minValue = 0;
    private float maxValue = 300;


    private boolean showMarkerBig = true;
    private boolean showMarkerSmall = true;
    private float markerBigSize;
    private float markerSmallSiae;
    private int markerBigColor;
    private int markerSmallColor;
    private float markerWidth;

    private boolean showMarkerText;
    private float markerTextSize;
    private int markerTextColor;

    private boolean showCenterPoint;
    private float centerPointSize;
    private int centerPointColor;

    private float headWidth;
    private int headColor;

    private float textSize;
    private int textColor;
    private int textPadding;
    private  float value;

    private int backgroundColor;

    private ObjectAnimator animator;
    private Interpolator interpolator;
    private float currentPoint;

    RectF arcRect = new RectF();


    // ===========================================================
    // Constructors
    // ===========================================================
    public SignalMeter(Context context) {
        super(context);
        init(context, null);
    }

    public SignalMeter(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);

    }

    public SignalMeter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================
    public float getValue() {
        return value;

    }

    public void setValue(float value) {
        this.value = value;
        invalidate();
    }

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================
    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {

        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        final int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        final int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        final int chosenWidth = getDimension(widthMode, widthSize);
        final int chosenHeight = getDimension(heightMode, heightSize);
        setMeasuredDimension(chosenWidth, chosenHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        float drawableWidth = getWidth();
        float drawableHeight = getHeight();



        float length = (Math.min(drawableWidth, drawableHeight));
        float radius = length / 2 + length / 10;
//        float radius = 3f/4 * length;


        float markerTextHeight = 0;
        float centerX = drawableWidth / 2;
        float centerY = 3.0f/4 * length;
        float halfWidth = centerX;
        float halfHight = centerY;
        float padding = 0f;
        
        //---------------------------------------------

        //background
        p.setColor(backgroundColor);
        canvas.drawRect(0,0,length, 3.0f/4 * length, p);

        arcRect.left = (radius - centerX) * -1;
        arcRect.top = centerY - radius;
        arcRect.right = drawableWidth + radius - centerX;
        arcRect.bottom = radius * 2 + arcRect.top;
        arcPaint.setStyle(Paint.Style.STROKE);
        arcPaint.setStrokeWidth(6.0f);
        arcPaint.setColor(Color.BLACK);
        canvas.drawArc(arcRect, -135.0f, 90.0f, false, arcPaint);
        arcPaint.setColor(Color.RED);
        arcPaint.setStrokeWidth(25.0f);
        canvas.drawArc(arcRect, -45.0f, -30.0f, false, arcPaint);
        arcRect.top += 15.0f;
        arcRect.bottom -= 15.0f;
        arcRect.left += 15.0f;
        arcRect.right -= 15.0f;
        arcPaint.setStrokeWidth(20.0f);
        arcPaint.setColor(Color.GRAY);
        canvas.drawArc(arcRect, -135.0f, 15.0f, false, arcPaint);
        arcPaint.setColor(Color.YELLOW);
        canvas.drawArc(arcRect, -120.0f, 45.0f, false, arcPaint);
        arcPaint.setColor(Color.GREEN);
        canvas.drawArc(arcRect, -75.0f, 30.0f, false, arcPaint);


        radius-=padding;
        p.setStrokeWidth(markerWidth);
        p.setColor(Color.parseColor("#3F51B5"));

        canvas.save();
        
        //----------------------------------------------
        //draw Big markers and Text
        if (showMarkerBig || showMarkerText) {
            markerTextPaint.setColor(markerTextColor);
            markerTextPaint.setTextSize(markerTextSize);

            float drawMarkerTextValue = minValue;
//            float difference = (maxValue - minValue) / 10;
            float difference = 10;


            canvas.rotate(-40, halfWidth, halfHight);
            for (int i = -40; i <= 40; i = i + 10) {
                if (showMarkerBig) {
                    if (i > 10) {
                        markerBigColor = Color.RED;
                        markerBigSize = 20.f;
                    }
                    p.setColor(markerBigColor);
                    p.setStrokeWidth(4f);
                    canvas.drawLine(halfWidth , centerY -radius -4, halfWidth, centerY-radius - markerBigSize - 4, p);
                }
                if (showMarkerText) {
                    String text = (int) drawMarkerTextValue -100 + "";
                    Rect bounds = new Rect();
                    markerTextPaint.getTextBounds(text, 0, text.length(), bounds);
                    markerTextHeight = bounds.height();
                    int width = bounds.width();
                    canvas.drawText(text, halfWidth - width / 2, centerY - radius - markerBigSize - markerTextHeight + padding, markerTextPaint);


                    drawMarkerTextValue += difference;
                }
                canvas.rotate(10, halfWidth, halfHight);
            }
            markerBigColor = Color.BLACK;
            markerBigSize = dpToPx(7);
            canvas.restore();
        }



        //draw small markers
        if (showMarkerSmall) {
            canvas.save();
            p.setColor(markerSmallColor);
            canvas.rotate(-40, halfWidth, halfHight);
            for (int i = -40; i <= 40; i = i + 2) {
                if (i > 14) {
                    markerSmallColor = Color.RED;
                    markerSmallSiae = 18.0f;
                    p.setColor(markerSmallColor);
                }
                canvas.drawLine(halfWidth ,  centerY - radius - markerSmallSiae, halfWidth, centerY - radius, p);
                canvas.rotate(2, halfWidth, halfHight);
            }
            markerSmallColor = Color.BLACK;
            markerSmallSiae = dpToPx(4);
            canvas.restore();
        }

        //draw value text
        p.setColor(textColor);
//        String text = (int) (value - 100) + "";
        String text = "dBm";
        Rect bounds = new Rect();
        p.setFakeBoldText(true);
        p.setTextSize(textSize);
        p.getTextBounds(text, 0, text.length(), bounds);
        int textheight = bounds.height();
        int textwidth = bounds.width();
        canvas.drawText(text, halfWidth - textwidth / 2, centerY/2 +textPadding+textSize, p);

        //draw head
        p.setColor(headColor);
        canvas.save();
        float rotation = ((value - minValue) / (maxValue - minValue)) * 80.0f;
        rotation = value < minValue ? 0 : rotation;
        rotation = value > maxValue ? 80.0f : rotation;
        canvas.rotate(rotation + 320.0f, halfWidth, halfHight);
        p.setStrokeWidth(headWidth);
        canvas.drawLine(halfWidth, halfHight - dpToPx(6), halfWidth , halfHight - radius, p);
        canvas.restore();

        //draw center point
        if (showCenterPoint) {
            p.setColor(centerPointColor);
            canvas.drawCircle(halfWidth, halfHight, centerPointSize, p);
        }

        super.onDraw(canvas);
    }
    @Override
    public Parcelable onSaveInstanceState() {

        Parcelable superState = super.onSaveInstanceState();

        SavedState state = new SavedState(superState);
        state.value = this.value;
        return state;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {

        if (!(state instanceof SavedState)) {
            super.onRestoreInstanceState(state);
            return;
        }

        SavedState savedState = (SavedState) state;
        super.onRestoreInstanceState(savedState.getSuperState());

        this.value = savedState.value;
    }
    // ===========================================================
    // Methods
    // ===========================================================
    private void init(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SignalMeter);
        showMarkerBig = typedArray.getBoolean(R.styleable.SignalMeter_sm_showMarkerBig, true);
        showMarkerSmall = typedArray.getBoolean(R.styleable.SignalMeter_sm_showMarkerSmall, true);
        markerBigSize = typedArray.getDimensionPixelSize(R.styleable.SignalMeter_sm_markerBigSzie, getResources().getDimensionPixelSize(R.dimen.sm_big_marker));
        markerSmallSiae = typedArray.getDimensionPixelSize(R.styleable.SignalMeter_sm_markerSmallSzie, getResources().getDimensionPixelSize(R.dimen.sm_small_marker));
        markerBigColor = typedArray.getColor(R.styleable.SignalMeter_sm_markerBigColor, Color.parseColor("#3F51B5"));
        markerSmallColor = typedArray.getColor(R.styleable.SignalMeter_sm_markerSmallColor, Color.parseColor("#B71C1C"));
        markerWidth = getResources().getDimensionPixelSize(R.dimen.sm_marker_width);

        showMarkerText = typedArray.getBoolean(R.styleable.SignalMeter_sm_showMarkerText, true);
        markerTextSize = typedArray.getDimensionPixelSize(R.styleable.SignalMeter_sm_markerTextSzie, getResources().getDimensionPixelSize(R.dimen.sm_marker_text_size));

        markerTextColor = typedArray.getColor(R.styleable.SignalMeter_sm_markerTextColor, Color.parseColor("#3F51B5"));

        showCenterPoint = typedArray.getBoolean(R.styleable.SignalMeter_sm_showCenterPoint, true);
        centerPointSize = typedArray.getDimensionPixelSize(R.styleable.SignalMeter_sm_centerPointSize, getResources().getDimensionPixelSize(R.dimen.sm_center_point_size));
        centerPointColor = typedArray.getColor(R.styleable.SignalMeter_sm_centerPointColor, Color.parseColor("#3F51B5"));

        headWidth = typedArray.getDimensionPixelSize(R.styleable.SignalMeter_sm_headWidth, getResources().getDimensionPixelSize(R.dimen.sm_head_width));
        headColor = typedArray.getColor(R.styleable.SignalMeter_sm_headColor, Color.parseColor("#B71C1C"));


        minValue = typedArray.getFloat(R.styleable.SignalMeter_sm_minValue, 0);
        maxValue = typedArray.getFloat(R.styleable.SignalMeter_sm_maxValue, 300);
        value = typedArray.getFloat(R.styleable.SignalMeter_sm_value, minValue);
        currentPoint = value;

        textSize = typedArray.getDimension(R.styleable.SignalMeter_sm_textSize, getResources().getDimensionPixelSize(R.dimen.sm_text_size));
        textColor = typedArray.getColor(R.styleable.SignalMeter_sm_textColor, Color.parseColor("#3F51B5"));
        textPadding = getResources().getDimensionPixelSize(R.dimen.sm_text_padding);

        backgroundColor=typedArray.getColor(R.styleable.SignalMeter_sm_backgroundColor, Color.parseColor("#FFFFFF"));
        arcPaint.setAntiAlias(true);
    }

    private int getDimension(final int mode, final int size) {
        switch (mode) {
            case MeasureSpec.AT_MOST:
            case MeasureSpec.EXACTLY:
                return size;
            case MeasureSpec.UNSPECIFIED:
            default:
                return DEFAULT_SIZE;
        }
    }

    public void moveHeadTo(float value) {
        if(animator!=null&&animator.isRunning()){
            animator.cancel();
        }
        currentPoint = this.value;
        this.value = value;
        movePointer();
    }

    public void setInterpolator(Interpolator i){
        interpolator=i;
    }
    private int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    private void movePointer() {
        animator = ObjectAnimator.ofFloat(this, "value", currentPoint, value);
        int duration=(int)((Math.abs(value-currentPoint)/maxValue)*4000);
        animator.setDuration(duration);
        if(interpolator!=null){
            animator.setInterpolator(interpolator);
        }
        animator.start();
    }

    public static class SavedState extends BaseSavedState {
        private float value;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            this.value = in.readFloat();
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeFloat(this.value);
        }

        public static final Creator<SavedState> CREATOR =
                new Creator<SavedState>() {
                    public SavedState createFromParcel(Parcel in) {
                        return new SavedState(in);
                    }

                    public SavedState[] newArray(int size) {
                        return new SavedState[size];
                    }
                };
    }
}