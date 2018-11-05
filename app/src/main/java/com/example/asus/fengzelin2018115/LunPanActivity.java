package com.example.asus.fengzelin2018115;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

public class LunPanActivity extends View implements View.OnClickListener {

    private Paint starPaint;
    private Paint mPaint;
    private RotateAnimation mRotateAnimation;
    private int mWidth;
    private int mPadding;
    private boolean isstart=false;
    private RectF rectF;
    private int defStyle;
    private String str = "Start";
    private String[] contents = new String[]{"一等奖", "二等奖", "三等奖", "四等奖", "参与奖", "谢谢参与"};
    private int mColor;

    public LunPanActivity(Context context) {
        this(context, null);
    }

    public LunPanActivity(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LunPanActivity(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
        initAnim();
        setOnClickListener(this);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ChangColor, defStyleAttr, 0);
        mColor = typedArray.getColor(R.styleable.ChangColor_mainColor, getResources().getColor(R.color.colorPrimary));
    }


    private void initPaint() {
        starPaint = new Paint();
        starPaint.setStyle(Paint.Style.STROKE);
        starPaint.setColor(Color.WHITE);
        starPaint.setAntiAlias(true);
        starPaint.setStrokeWidth(3);

        mPaint = new Paint();
        mPaint.setStrokeWidth(3);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
    }

    private void initAnim() {
        mRotateAnimation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5F);
        mRotateAnimation.setRepeatCount(-1);
        mRotateAnimation.setFillAfter(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mPadding = 5;
        initRect();
    }

    private void initRect() {
        rectF = new RectF(0, 0, mWidth, mWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        绘制圆
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(mWidth / 2, mWidth / 2, mWidth / 2 - mPadding, mPaint);
//        绘制六个椭圆
        mPaint.setStyle(Paint.Style.FILL);

        initArc(canvas);

//       绘制里面的小圆
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(mWidth / 2, mWidth / 2, 50, mPaint);

        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(24);
        Rect rect = new Rect();
        mPaint.getTextBounds(str, 0, str.length(), rect);
        int strWidth = rect.width();
        int strHeight = rect.height();
        canvas.drawText(str, mWidth / 2 - 25 + 25 - strWidth / 2, mWidth / 2 + strHeight / 2, mPaint);
    }

    private void initArc(Canvas canvas) {
        for (int i = 0; i < 6; i++) {
            mPaint.setColor(colors[i]);
            canvas.drawArc(rectF, (i - 1) * 60 + 60, 60, true, mPaint);
        }
        for (int i = 0; i < 6; i++) {
            mPaint.setColor(Color.BLACK);
            Path path = new Path();
            path.addArc(rectF, (i - 1) * 60 + 60, 60);
            canvas.drawTextOnPath(contents[i], path, 60, 60, mPaint);
        }
    }

    public int[] colors = new int[]{Color.parseColor("#8EE5EE"),
            Color.parseColor("#FFD700"),
            Color.parseColor("#FFD398"),
            Color.parseColor("#FF8247"),
            Color.parseColor("#FF34B3"),
            Color.parseColor("#F0E68C")};
    public void onClick(View view){
        if(!isstart){
            isstart=true;
            mRotateAnimation.setDuration(1000);
            mRotateAnimation.setInterpolator(new LinearInterpolator());
            startAnimation(mRotateAnimation);
        }else{
            isstart=false;
            stopAnim();
        }
    }

    private void stopAnim() {
        clearAnimation();
    }
}
