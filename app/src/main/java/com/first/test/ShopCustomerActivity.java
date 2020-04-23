package com.first.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.first.test.webceshi.ResponseCustomerList;
import com.first.test.webceshi.ResponseShop;
import com.first.test.widget.CustomDialog2;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class ShopCustomerActivity extends AppCompatActivity {

    private ImageView mBtnExit;
    private ListView mshopCustomer,mLvShopList;
    private TextView mTvChange ,mTvShopName;
    private ImageView mIvCreateOrder;
    private String mToken,mShopId,mStrShopName;

    private List<ResponseShop.ValueShop> mShopName = new ArrayList<>();
    private List<ResponseCustomerList.ValuesCus> mCustomerList = new ArrayList<>();

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_customer);

        //退出
        mBtnExit = findViewById(R.id.btn_exit);
        mBtnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
                mEditor =mSharedPreferences.edit();
                mEditor.remove("nick");
                mEditor.remove("password");
                mEditor.remove("token");

                mEditor.apply();
                Intent intent = new Intent(ShopCustomerActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });



        //顾客列表
        //get
        mSharedPreferences = getSharedPreferences("data",MODE_PRIVATE);

        mToken =  mSharedPreferences.getString("token","");
        mShopId = mSharedPreferences.getString("shopId","");
        mStrShopName = mSharedPreferences.getString("shopName","");

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .get()
                .url("https:retail-proxy.yipiaoyun.cn/app/persons/list?shopId="+"52")
                .header("Authorization", mToken)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("shibai ");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String res = response.body().string();

                System.out.println("chenggong ");
                System.out.println(res);

                Gson gson = new Gson();
                ResponseCustomerList responseCustomerList = gson.fromJson(res, ResponseCustomerList.class);

                mCustomerList = responseCustomerList.getValue().getValues();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //创建顾客列表
                        mshopCustomer = findViewById(R.id.lv_shop_customer);
                        mshopCustomer.setAdapter(new ListAdapterCustomer(ShopCustomerActivity.this, mCustomerList));


                        //顾客详情
                        mshopCustomer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Toast.makeText(ShopCustomerActivity.this, "" + i, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ShopCustomerActivity.this, CustomerActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });

                    }
                });

            }
        });


        //切换门店
        mTvChange = findViewById(R.id.tv_change);
        mTvShopName = findViewById(R.id.tv_shop_name);
        mTvShopName.setText(mStrShopName);
        mTvChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //获取门店列表
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .get()
                        .url("https:retail-proxy.yipiaoyun.cn/app/shops/list")
                        .header("Authorization", mToken)
                        .build();
                Call call = client.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        System.out.println("shibai");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String res = response.body().string();
                        System.out.println(res);
                        Gson gson = new Gson();
                        ResponseShop responseShop = gson.fromJson(res, ResponseShop.class);

                        mShopName = responseShop.getValue();

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                CustomDialog2 customDialog = new CustomDialog2(ShopCustomerActivity.this, R.style.MyCustDialog, mShopName);

                                customDialog.setTitle("选择门店").setCancel("关闭", new CustomDialog2.IOnCancelListener() {
                                    @Override
                                    public void OnCancel(CustomDialog2 customDialog2) {

                                    }
                                }).show();



//                                //延迟执行点击
//                                new Thread(new Runnable() {
//                                    @Override
//                                    public void run() {
//
//                                        try {
//                                            Thread.sleep(5000); // 休眠1秒
//                                        } catch (InterruptedException e) {
//                                            e.printStackTrace();
//                                        }
//
//                                        mLvShopList = findViewById(R.id.lv_shop_list);
//                                        mLvShopList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                                            @Override
//                                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                                                Intent intent = new Intent(ShopCustomerActivity.this,ShopCustomerActivity.class);
//
//                                                mSharedPreferences = getSharedPreferences("data",MODE_PRIVATE);
//                                                mEditor =mSharedPreferences.edit();
//                                                mEditor.putString("shopId",mShopName.get(i).getShopId());
//                                                mEditor.putString("shopName",mShopName.get(i).getName());
//                                                mEditor.apply();
//                                                startActivity(intent);
//                                                finish();
//                                            }
//
//                                        });
//
//                                    }
//                                }).start();




                            }
                        });


                    }
                });


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
