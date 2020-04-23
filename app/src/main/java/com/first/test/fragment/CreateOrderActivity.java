package com.first.test.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;

import android.util.TypedValue;
import android.view.View;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import android.widget.RelativeLayout;
import android.widget.TextView;

import com.first.test.R;
import com.first.test.ShopCustomerActivity;
import com.first.test.webceshi.ResponseShop;
import com.first.test.widget.CustomDialog2;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class CreateOrderActivity extends AppCompatActivity {

//    private TextView mTvadd, mTvCreate;
//    private CreateFragment mCreateFragment;
//    private CreateSuccFragment mCreateSuccFragment;
    private LinearLayout mLlCreateOrder, mLlCreateOrderSucc, mLlContainer, mLlContainerSucc, mLlBottom, mLlBottom2;
    private TextView mTvClean, mTvCreate, mTvTotal, mTvContinue, mTvTitle,mTvChange,mTvShopName;
    private ImageView mIvback;

    private List<ResponseShop.ValueShop> mShopName = new ArrayList<>();

    private List<Integer> mListTotal = new LinkedList<>();
    private int mTotal;

    private String mToken,mShopId,mStrShopName;

    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);

        //订单列表
        mLlCreateOrder = findViewById(R.id.ll_create_order);
        mLlCreateOrderSucc = findViewById(R.id.ll_create_order_succ);

        //中间整个布局
        mLlContainer = findViewById(R.id.ll_container);
        mLlContainerSucc = findViewById(R.id.ll_container_succ);
        //底部整个布局
        mLlBottom = findViewById(R.id.ll_bottom);
        mLlBottom2 = findViewById(R.id.ll_bottom_2);

        findViewById(R.id.tv_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addView();

            }
        });

        mTvClean = findViewById(R.id.tv_clean);
        mTvCreate = findViewById(R.id.tv_create);
        mTvTitle = findViewById(R.id.tv_title);

        //创建订单
        mTvTotal = findViewById(R.id.tv_total);
        mTvCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListTotal.size() == 0) {
                    return;
                } else {
                    //顶部
                    mIvback.setVisibility(View.GONE);
                    mTvTitle.setText("订单详情");
                    //中间
                    mLlContainer.setVisibility(View.GONE);
                    mLlContainerSucc.setVisibility(View.VISIBLE);

                    //总额
                    for (int a = 0; a < mListTotal.size(); a++) {
                        mTotal += mListTotal.get(a);
                        mTvTotal.setText(String.valueOf(mTotal));
                    }
                    //底部
                    mLlBottom.setVisibility(View.GONE);
                    mLlBottom2.setVisibility(View.VISIBLE);

                }
            }
        });
        //继续创建订单
        mTvContinue = findViewById(R.id.tv_continue);
        mTvContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLlCreateOrder.removeAllViews();
                mLlCreateOrderSucc.removeAllViews();
                mListTotal.clear();
                mTotal=0;

                mLlContainer.setVisibility(View.VISIBLE);
                mLlContainerSucc.setVisibility(View.GONE);


                mLlBottom.setVisibility(View.VISIBLE);
                mLlBottom2.setVisibility(View.GONE);

                mIvback.setVisibility(View.VISIBLE);
                mTvTitle.setText("创建订单");

            }
        });


        //清除
        mTvClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLlCreateOrder.removeAllViews();
            }
        });
        //返回
        mIvback = findViewById(R.id.iv_back);
        mIvback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateOrderActivity.this, ShopCustomerActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //
        mSharedPreferences = getSharedPreferences("data",MODE_PRIVATE);

        mToken =  mSharedPreferences.getString("token","");
        mShopId = mSharedPreferences.getString("shopId","");
        mStrShopName = mSharedPreferences.getString("shopName","");

        mTvShopName =findViewById(R.id.tv_shop_name);


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

                                CustomDialog2 customDialog = new CustomDialog2(CreateOrderActivity.this, R.style.MyCustDialog, mShopName);

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


    }

    /**
     * 按钮一的点击事件
     */
    private void addView() {

        String[] commodity = {"旺仔小盲头", "哇哈哈八宝粥", "悦动力苹果汁", "三得利乌龙茶", "纸巾"};
        int[] money = {3, 8, 3, 4, 1};

        RelativeLayout relativeLayout = new RelativeLayout(CreateOrderActivity.this);
        RelativeLayout relativeLayout_succ = new RelativeLayout(CreateOrderActivity.this);

        relativeLayout.setBackgroundColor(getResources().getColor(R.color.bg_list_blue));
        relativeLayout_succ.setBackgroundColor(getResources().getColor(R.color.bg_list_blue));


        //随机抽个东西
        Random random = new Random();
        int i = random.nextInt(5);
        //dp2dx
        //设置EditText的宽度为指定大小宽度,要相应的dp转化为px
        Resources r = getResources();
        int dp50 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());
        int dp20 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());
        int dp13 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 13, getResources().getDisplayMetrics());
        int dp10 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());


        //double
        TextView tv_commodity = new TextView(this);
        tv_commodity.setText(commodity[i]);
        tv_commodity.setTextSize(16);
        tv_commodity.setTextColor(getResources().getColor(R.color.black666));
        tv_commodity.setPadding(dp10, dp13, 0, dp13);

        TextView tv_commodity_succ = new TextView(this);
        tv_commodity_succ.setText(commodity[i]);
        tv_commodity_succ.setTextSize(16);
        tv_commodity_succ.setTextColor(getResources().getColor(R.color.black666));
        tv_commodity_succ.setPadding(dp10, dp13, 0, dp13);

        RelativeLayout.LayoutParams tvCommodityParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, dp50);
        RelativeLayout.LayoutParams tvCommodityParams_succ = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, dp50);


        //double
        String moneyS = String.valueOf(money[i]);
        TextView tv_money = new TextView(this);
        tv_money.setText(moneyS);
        tv_money.setTextSize(16);
        tv_money.setTextColor(getResources().getColor(R.color.orange_money));
        tv_money.setPadding(0, dp13, dp10, dp13);

        RelativeLayout.LayoutParams tvMoneyParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, dp50);
        tvMoneyParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);


        TextView tv_money_succ = new TextView(this);
        tv_money_succ.setText(moneyS);
        tv_money_succ.setTextSize(16);
        tv_money_succ.setTextColor(getResources().getColor(R.color.orange_money));
        tv_money_succ.setPadding(0, dp13, dp10, dp13);

        RelativeLayout.LayoutParams tvMoneyParams_succ = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, dp50);
        tvMoneyParams_succ.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        //总额
        mListTotal.add(money[i]);


        relativeLayout.addView(tv_commodity, tvCommodityParams);
        relativeLayout.addView(tv_money, tvMoneyParams);
        mLlCreateOrder.addView(relativeLayout, -1);

        relativeLayout_succ.addView(tv_commodity_succ, tvCommodityParams_succ);
        relativeLayout_succ.addView(tv_money_succ, tvMoneyParams_succ);
        mLlCreateOrderSucc.addView(relativeLayout_succ, -1);

    }


}
    /**
     *格式化事件
     */
//    public String dateToStamp(long s) {
//        String res;
//        try {
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            Date date = new Date(s);
//            res = simpleDateFormat.format(date);
//        } catch (Exception e) {
//            return "";
//        }
//        return res;




//        //实例化createfragment
//        mCreateFragment = new CreateFragment();
//        //把createfragment添加到activity
//        getSupportFragmentManager().beginTransaction().add(R.id.content, mCreateFragment).commitAllowingStateLoss();

        //改变fragment
//        mTvCreate = findViewById(R.id.tv_create);
//        mCreateSuccFragment = new CreateSuccFragment();
//        mTvCreate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (mCreateSuccFragment == null) {
//                    mCreateSuccFragment = new CreateSuccFragment();
//                }
//                getSupportFragmentManager().beginTransaction().replace(R.id.content, mCreateSuccFragment).commitAllowingStateLoss();
//            }
//        });









