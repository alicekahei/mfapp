package com.first.test.fragment;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.widget.ScrollView;

public class AutoFitScrollView extends ScrollView {
    private Context mContext;

    public AutoFitScrollView (Context context) {
        super(context);
        init(context);
    }

    public AutoFitScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

    }

    public AutoFitScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        try {
            //最大高度显示为屏幕内容高度的一半
            Display display = ((Activity) mContext).getWindowManager().getDefaultDisplay();
            DisplayMetrics d = new DisplayMetrics();
            display.getMetrics(d);
            //此处是关键，设置控件高度不能超过屏幕高度一半（d.heightPixels / 2）（在此替换成自己需要的高度）
            int dp300 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, getResources().getDisplayMetrics());

            heightMeasureSpec = MeasureSpec.makeMeasureSpec(dp300, MeasureSpec.AT_MOST);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //重新计算控件高、宽
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
