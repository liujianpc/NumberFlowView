package com.xiaopeng.numberrainview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Date: 2018/4/24
 * Created by LiuJian
 * @author XP-PC-XXX
 */

public class NumberRainFlowView extends LinearLayout {

    private int mNormalColor = Color.GREEN;
    private int mHightLightColor = Color.YELLOW;
    private float mTextSize = 30 * getResources().getDisplayMetrics().density;
    private  LinearLayout.LayoutParams mLayoutParams;

    private final int mCount = (int) (getResources().getDisplayMetrics().widthPixels / mTextSize);

    public NumberRainFlowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            parseAttribute(context, attrs);
        }
        initNormal();
    }

    private void initNormal() {
        setOrientation(HORIZONTAL);
        setBackgroundColor(Color.BLACK);
        mLayoutParams =
                new LinearLayout.LayoutParams((int) mTextSize + 10, getResources().getDisplayMetrics().heightPixels);
    }

    private void parseAttribute(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NumberRain);
        mNormalColor = typedArray.getColor(R.styleable.NumberRain_normalColor, Color.GREEN);
        mHightLightColor = typedArray.getColor(R.styleable.NumberRain_highLightColor, Color.RED);
        mTextSize = typedArray.getDimension(R.styleable.NumberRain_textSize, mTextSize);
        typedArray.recycle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        addRainItems();
    }

    /**
     * 添加每一列
     */
    private void addRainItems() {

        /**
         * 遍歷每一列
         */
        for (int i = 0; i < mCount; i++) {
            NumberRainItem rainItem = new NumberRainItem(getContext(), null);
            rainItem.setHighlightColor(mHightLightColor);
            rainItem.setTextSize(mTextSize);
            rainItem.setNormalColor(mNormalColor);
            rainItem.setStartOffset((int) (Math.random() * 10000));
            addView(rainItem, mLayoutParams);
        }
    }
}
