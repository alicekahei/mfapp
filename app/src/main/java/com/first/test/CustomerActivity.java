package com.first.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;


import com.first.test.listview.ListAdapterBehavior;

public class CustomerActivity extends AppCompatActivity {

    private ImageView mIvback;
    private ListView mLvBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);


        mLvBehavior = findViewById(R.id.lv_behavior);
        mLvBehavior.setAdapter(new ListAdapterBehavior(CustomerActivity.this));



        //返回
        mIvback = findViewById(R.id.iv_back);
        mIvback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CustomerActivity.this, ShopCustomerActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
