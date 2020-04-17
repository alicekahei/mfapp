package com.first.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.first.test.listview.MyListAdapter;

import java.util.ArrayList;
import java.util.List;

public class SelectShopActivity extends AppCompatActivity {
    private ImageView mBtnExit;
    private ListView mLvShopName;
    private List<String> shopName = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_shop);

        mBtnExit = findViewById(R.id.btn_exit);

        mBtnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectShopActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        shopName.add("黄龙时代广场店");
        shopName.add("双光心存店");
        shopName.add("梅菜的死奔驰店");
        shopName.add("春天花花有热点");
        mLvShopName = findViewById(R.id.lv_select_shop);
        //ceshi

        mLvShopName.setAdapter(new MyListAdapter(SelectShopActivity.this,shopName));




        mLvShopName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(SelectShopActivity.this,"click"+shopName.get(i),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SelectShopActivity.this,ShopCustomerActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }



}
