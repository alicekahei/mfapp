package com.first.test.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

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


    }
    @Override
    public void show() {
        super.show();
    }

}
