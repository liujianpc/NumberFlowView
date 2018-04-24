package com.xiaopeng.numberrainview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Date: 2018/4/24
 * Created by LiuJian
 */

public class NumberRainFlowView extends LinearLayout {

    private int mNormalColor = Color.GREEN;
    private int hightLightColor = Color.YELLOW;
    private float textSize = 15 * getResources().getDisplayMetrics().density;

    public NumberRainFlowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            pareseAttrs(context, attrs);
        }
        initNormal();
    }

    private void initNormal() {
        setOrientation(HORIZONTAL);
        setBackgroundColor(Color.BLACK);
    }

    private void pareseAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NumberRain);
        mNormalColor = typedArray.getColor(R.styleable.NumberRain_normalColor, Color.GREEN);
        hightLightColor = typedArray.getColor(R.styleable.NumberRain_highLightColor, Color.RED);
        textSize = typedArray.getDimension(R.styleable.NumberRain_textSize, textSize);
        typedArray.recycle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        addRainItems();
    }

    private void addRainItems() {
        int count = (int) (getResources().getDisplayMetrics().widthPixels / textSize);
        for (int i = 0; i < count; i++) {

            NumberRainItem rainItem = new NumberRainItem(getContext(), null);
            rainItem.setmHighlightColor(hightLightColor);
            rainItem.setTextSize(textSize);
            rainItem.setmNormalColor(mNormalColor);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int) textSize + 10, getResources().getDisplayMetrics().heightPixels);
            rainItem.setStartOffset((int) (Math.random() * 1000));
            addView(rainItem, layoutParams);
        }
    }
}
