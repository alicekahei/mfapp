package com.first.test.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.first.test.R;

public class CustomDialog extends Dialog {
    public CustomDialog(Context context) {
        super(context);

    }

    public CustomDialog(Context context, int themeResId) {
        super(context, themeResId);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_custom_dialog);

        getWindow().setGravity(Gravity.BOTTOM); //显示在底部




        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();

        WindowManager.LayoutParams p = getWindow().getAttributes();
     //   p.width = d.getPhysicalWidth(); //设置dialog的宽度为当前手机屏幕的宽度
        getWindow().setAttributes(p);




    }

    @Override
    public void show() {
        super.show();
    }




}
