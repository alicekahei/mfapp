package com.first.test.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.first.test.R;
import com.first.test.ShopCustomerActivity;
import com.first.test.listview.MyListAdapter;
import com.first.test.webceshi.ResponseShop;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomDialog2 extends Dialog implements View.OnClickListener {


    private TextView mTvTitle, mTvCancel;
    private ListView mLvShopList;
    private IOnCancelListener cancelListener;
    private List<ResponseShop.ValueShop> shopName = new ArrayList<>();
    private Context context;

    public MyCallBack getMyCallBack() {
        return myCallBack;
    }

    public void setMyCallBack(MyCallBack myCallBack) {
        this.myCallBack = myCallBack;
    }

    private MyCallBack myCallBack;

    public CustomDialog2 setTitle(String title) {
        this.title = title;
        return this;
    }


    public CustomDialog2 setCancel(String cancel, IOnCancelListener listener) {
        this.cancel = cancel;
        cancelListener = listener;

        return this;
    }

    private String title, cancel;

    public CustomDialog2(Context context, int themeResId, List<ResponseShop.ValueShop> shopName) {
        super(context,themeResId);
        this.context= context;
        this.shopName = shopName;
    }

    public CustomDialog2(Context context, int themeResId) {
        super(context, themeResId);
    }

//    protected CustomDialog2(Context context, boolean cancelable, OnCancelListener cancelListener) {
//        super(context, cancelable, cancelListener);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_custom_dialog);


        mLvShopList = findViewById(R.id.lv_shop_list);
        mLvShopList.setAdapter(new MyListAdapter(getContext(), shopName));
//4。22晚上线先不写
//        mLvShopList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//                myCallBack.back(shopName.get(i).getShopId(),shopName.get(i).getName());
//
//                dismiss();
//
//
//            }
//        });


        getWindow().setGravity(Gravity.BOTTOM); //显示在底部
        //设置宽度
        WindowManager m = getWindow().getWindowManager();
        Display d = m.getDefaultDisplay();
        WindowManager.LayoutParams p = getWindow().getAttributes();
        Point size = new Point();
        d.getSize(size);
        p.width = size.x * 1;
        getWindow().setAttributes(p);


        mTvTitle = findViewById(R.id.tv_title);
        mTvCancel = findViewById(R.id.tv_cancel);
        mLvShopList = findViewById(R.id.lv_shop_list);

        if (!TextUtils.isEmpty(title)) {
            mTvTitle.setText(title);
        }
        if (!TextUtils.isEmpty(cancel)) {
            mTvCancel.setText(cancel);
        }

        mTvCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                if (cancelListener != null) {
                    cancelListener.OnCancel(this);
                }
                dismiss();
                break;
        }
    }

    public interface IOnCancelListener {
        void OnCancel(CustomDialog2 customDialog2);

    }

    public interface MyCallBack{
        void back(String shopId,String shopName);
    }

}
