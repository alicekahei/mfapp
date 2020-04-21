package com.first.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.first.test.fragment.CreateOrderActivity;
import com.first.test.listview.ListAdapterCustomer;
import com.first.test.listview.MyListAdapter;

import com.first.test.widget.CustomDialog2;

import java.util.ArrayList;
import java.util.List;

public class ShopCustomerActivity extends AppCompatActivity {

    private ImageView mBtnExit;
    private ListView mshopCustomer,mLvShopName;
    private TextView mTvChange;
    private ImageView mIvCreateOrder;

    private List<String> shopName = new ArrayList<>();

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
                finish();
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



        //数据放入
        shopName.add("黄龙时代广场店");
        shopName.add("双光心存店");
        shopName.add("梅菜的死奔驰店");
        shopName.add("春天花花有热点");
        //切换门店
        mTvChange = findViewById(R.id.tv_change);
        mTvChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomDialog2 customDialog = new CustomDialog2(ShopCustomerActivity.this,R.style.MyCustDialog,shopName);

                customDialog.setTitle("选择门店").setCancel("关闭", new CustomDialog2.IOnCancelListener() {
                    @Override
                    public void OnCancel(CustomDialog2 customDialog2) {

                    }
                }).show();






//                mLvShopName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                        Toast.makeText(ShopCustomerActivity.this,"click"+shopName.get(i),Toast.LENGTH_SHORT).show();
//
//                    }
//
//                });
            }
        });


        //创建订单
        mIvCreateOrder = findViewById(R.id.iv_create_order);
        mIvCreateOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShopCustomerActivity.this, CreateOrderActivity.class);
                startActivity(intent);
            }
        });

    }


}
