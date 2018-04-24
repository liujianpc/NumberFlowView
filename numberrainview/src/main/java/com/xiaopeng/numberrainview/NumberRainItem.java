package com.xiaopeng.numberrainview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Date: 2018/4/24
 * Created by LiuJian
 */

public class NumberRainItem extends View {

    private Paint mPaint;
    private float textSize = 15 * getResources().getDisplayMetrics().density;
    private int mNormalColor = Color.GREEN;
    private int mHighlightColor = Color.YELLOW;
    private long startOffset = 0L;

    private float mNowHeight = 0f;
    private int mHighlightNumIndex = 0;

    public NumberRainItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attts) {

        if (attts != null) {
            parseAttrs(context, attts);
        }
        initPaint();
    }


    private void initPaint() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    private void parseAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NumberRainItem);
        mNormalColor = typedArray.getColor(R.styleable.NumberRainItem_normalColor, Color.GREEN);
        mHighlightColor = typedArray.getColor(R.styleable.NumberRain_highLightColor, Color.YELLOW);
        textSize = typedArray.getDimension(R.styleable.NumberRainItem_textSize, textSize);
        typedArray.recycle();
    }

    public void setmPaint(Paint mPaint) {
        this.mPaint = mPaint;
        postInvalidateDelayed(1000);
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
        postInvalidateDelayed(1000);
    }

    public void setmNormalColor(int mNormalColor) {
        this.mNormalColor = mNormalColor;
        postInvalidateDelayed(1000);
    }

    public void setmHighlightColor(int mHighlightColor) {
        this.mHighlightColor = mHighlightColor;
        postInvalidateDelayed(1000);
    }

    public void setStartOffset(int startOffset) {
        this.startOffset = startOffset;
        postInvalidateDelayed(1000);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        configPaint();
        if (isShowAllNumber()) {
            drawTotalNumbers(canvas);
        } else {
            drawPartNumbers(canvas);
        }
    }

    private void configPaint() {
        mPaint.setTextSize(textSize);
        mPaint.setColor(mNormalColor);
    }

    private void drawPartNumbers(Canvas canvas) {
        int count = (int) (mNowHeight / textSize);
        mNowHeight += textSize;
        drawNumbers(canvas, count);
    }

    private void drawTotalNumbers(Canvas canvas) {
        int count = (int) (getResources().getDisplayMetrics().heightPixels / textSize);
        drawNumbers(canvas, count);
    }

    private void drawNumbers(Canvas canvas, int count) {

        if (count == 0) {
            postInvalidateDelayed(startOffset);
        } else {

            float offset = 0f;
            int[] numbers = new int[count];

            for (int i = 0; i < count; i++) {
                String text = String.valueOf((int) (Math.random() * 10));
                if (mHighlightNumIndex == i) {
                    mPaint.setColor(mHighlightColor);
                    mPaint.setShadowLayer(10f, 0f, 0f, mHighlightColor);
                } else {
                    mPaint.setColor(mNormalColor);
                    mPaint.setShadowLayer(10f, 0f, 0f, mNormalColor);
                }

                canvas.drawText(text, 0f, textSize + offset, mPaint);
                offset += textSize;

            }
            if (!isShowAllNumber()) {
                mHighlightNumIndex++;
            } else {
                mHighlightNumIndex = (++mHighlightNumIndex) % count;
            }
            postInvalidateDelayed(100L);
        }
    }

    private boolean isShowAllNumber() {
        return mNowHeight >= getResources().getDisplayMetrics().heightPixels;
    }
}
