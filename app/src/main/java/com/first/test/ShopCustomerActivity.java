package com.first.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.first.test.listview.ListAdapterCustomer;
import com.first.test.widget.CustomDialog;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListMap;

public class ShopCustomerActivity extends AppCompatActivity {

    private ImageView mBtnExit;
    private ListView mshopCustomer;
    private TextView mTvChange;
    private ImageView mIvCreateOrder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_customer);

        //退出
        mBtnExit = findViewById(R.id.btn_exit);
        mBtnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShopCustomerActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        //


        //顾客列表
        mshopCustomer = findViewById(R.id.lv_shop_customer);
        mshopCustomer.setAdapter(new ListAdapterCustomer(ShopCustomerActivity.this));
        mshopCustomer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ShopCustomerActivity.this,""+i,Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(ShopCustomerActivity.this,CustomerActivity.class);
                startActivity(intent);
            }
        });

        //切换门店
        mTvChange = findViewById(R.id.tv_change);
        mTvChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomDialog customDialog = new CustomDialog(ShopCustomerActivity.this);

                customDialog.show();
            }
        });


        //创建订单
        mIvCreateOrder = findViewById(R.id.iv_create_order);
        mIvCreateOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShopCustomerActivity.this,CreateOrderActivity.class);
                startActivity(intent);
            }
        });

    }


}
