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
 * @author XP-PC-XXX
 */

public class NumberRainItem extends View {

    private  final float DISPLAY_HEIGHT = getResources().getDisplayMetrics().heightPixels;
    private Paint mPaint;
    private float mTextSize = 30 * getResources().getDisplayMetrics().density;
    private int mNormalColor = Color.GREEN;
    private int mHighlightColor = Color.YELLOW;
    private long mStartOffset = 1000L;

    private float mNowHeight = 0f;
    private int mHighlightNumIndex = 0;
    private int mVerticalTTotalCount = (int) (DISPLAY_HEIGHT / mTextSize);

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
        mPaint.setTextSize(mTextSize);
        mPaint.setColor(mNormalColor);
    }

    private void parseAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NumberRainItem);
        mNormalColor = typedArray.getColor(R.styleable.NumberRainItem_normalColor, Color.GREEN);
        mHighlightColor = typedArray.getColor(R.styleable.NumberRain_highLightColor, Color.YELLOW);
        mTextSize = typedArray.getDimension(R.styleable.NumberRainItem_textSize, mTextSize);
        typedArray.recycle();
    }

    public void setPaint(Paint mPaint) {
        this.mPaint = mPaint;
        postInvalidateDelayed(1000);
    }

    public void setTextSize(float textSize) {
        this.mTextSize = textSize;
        postInvalidateDelayed(1000);
    }

    public void setNormalColor(int mNormalColor) {
        this.mNormalColor = mNormalColor;
        postInvalidateDelayed(1000);
    }

    public void setHighlightColor(int mHighlightColor) {
        this.mHighlightColor = mHighlightColor;
        postInvalidateDelayed(1000);
    }

    public void setStartOffset(int startOffset) {
        this.mStartOffset = startOffset;
        postInvalidateDelayed(1000);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //configPaint();
        if (isShowAllNumber()) {
            drawTotalNumbers(canvas);
        } else {
            drawPartNumbers(canvas);
        }
    }

    private void configPaint() {
        mPaint.setTextSize(mTextSize);
        mPaint.setColor(mNormalColor);
    }

    private void drawPartNumbers(Canvas canvas) {
        int count = (int) (mNowHeight / mTextSize);
        mNowHeight += mTextSize;
        drawNumbers(canvas, count);
    }

    private void drawTotalNumbers(Canvas canvas) {

        drawNumbers(canvas, mVerticalTTotalCount);
    }

    private void drawNumbers(Canvas canvas, int count) {

        if (count == 0) {
            postInvalidateDelayed(mStartOffset);
        } else {

            float offset = 0f;
            int[] numbers = new int[count];

            for (int i = 0; i < count; i++) {
                String text = String.valueOf((int) (Math.random() * 10));
                if (mHighlightNumIndex == i) {
                    mPaint.setColor(mHighlightColor);
                    //mPaint.setShadowLayer(10f, 0f, 0f, mHighlightColor);
                } else {
                    mPaint.setColor(mNormalColor);
                    //mPaint.setShadowLayer(10f, 0f, 0f, mNormalColor);
                }

                canvas.drawText(text, 0f, mTextSize + offset, mPaint);
                offset += mTextSize;

            }
            if (!isShowAllNumber()) {
                mHighlightNumIndex++;
            } else {
                mHighlightNumIndex = (++mHighlightNumIndex) % count;
            }
            postInvalidateDelayed(10L);
        }
    }

    private boolean isShowAllNumber() {
        return mNowHeight >= DISPLAY_HEIGHT;
    }
}
