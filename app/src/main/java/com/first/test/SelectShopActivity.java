package com.first.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.first.test.listview.MyListAdapter;
import com.first.test.webceshi.ResponseShop;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SelectShopActivity extends AppCompatActivity {
    private ImageView mBtnExit;
    private ListView mLvShopName;
    private String mToken =null;
    private List<ResponseShop.ValueShop> mShopName = new LinkedList<>();

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_shop);

        mSharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
        mToken = mSharedPreferences.getString("token","");



        //退出
        mBtnExit = findViewById(R.id.btn_exit);
        mBtnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mEditor =mSharedPreferences.edit();
                mEditor.remove("nick");
                mEditor.remove("password");
                mEditor.remove("token");

                mEditor.apply();
                Intent intent = new Intent(SelectShopActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //



        mLvShopName = findViewById(R.id.lv_select_shop);

        //获取门店列表
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .get()
                .url("https:retail-proxy.yipiaoyun.cn/app/shops/list")
                .header("Authorization",mToken)
                .build();
        Call call =client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("shibai");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String res = response.body().string();
                Gson gson = new Gson();
                ResponseShop responseShop  = gson.fromJson(res,ResponseShop.class);

                mShopName = responseShop.getValue();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mLvShopName.setAdapter(new MyListAdapter(SelectShopActivity.this,mShopName));

                    }
                });
            }
        });

        mLvShopName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(SelectShopActivity.this,"click"+mShopName.get(i).getName(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SelectShopActivity.this,ShopCustomerActivity.class);

                mSharedPreferences = getSharedPreferences("data",MODE_PRIVATE);

                mEditor =mSharedPreferences.edit();
                mEditor.putString("shopId",mShopName.get(i).getShopId());
                mEditor.putString("shopName",mShopName.get(i).getName());
                mEditor.apply();
                startActivity(intent);
                finish();
            }
        });


    }



}
