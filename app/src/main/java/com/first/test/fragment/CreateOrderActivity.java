package com.first.test.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import android.view.ViewGroup;
import android.widget.LinearLayout;

import android.widget.RelativeLayout;
import android.widget.TextView;

import com.first.test.R;

import java.util.Random;


public class CreateOrderActivity extends AppCompatActivity {

    //    private TextView mTvadd, mTvCreate;
//    private CreateFragment mCreateFragment;
//    private CreateSuccFragment mCreateSuccFragment;
    private LinearLayout mlllist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);


        mlllist = findViewById(R.id.ll_create_order);
        findViewById(R.id.tv_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addView();
            }
        });

    }

    /**
     * 按钮一的点击事件
     */
    private void addView() {
        String[] commodity = {"旺仔小盲头","哇哈哈八宝粥","悦动力苹果汁","三得利乌龙茶","纸巾"};
        String[] money = {"3","8","3","4","1"};
        RelativeLayout relativeLayout = new RelativeLayout(CreateOrderActivity.this);

        relativeLayout.setBackgroundColor(getResources().getColor(R.color.bg_list_blue));
        relativeLayout.setMinimumHeight(50);


        //随机抽个东西
        Random random=new Random();

        int i =  random.nextInt(5);

        TextView tv_commodity = new TextView(this);
        tv_commodity.setText(commodity[i]);
        tv_commodity.setTextSize(16);
        tv_commodity.setTextColor(getResources().getColor(R.color.black666));
        tv_commodity.setPadding(10,12,0,12);

        TextView tv_money = new TextView(this);
        tv_money.setText(money[i]);
        tv_money.setTextSize(16);
        tv_money.setTextColor(getResources().getColor(R.color.black666));
        tv_money.setPadding();
        //ceshi

        RelativeLayout rl =    new RelativeLayout(this);
        ViewGroup.MarginLayoutParams mp = new MarginLayoutParams(20,20);  //item的宽高
        mp.setMargins(0, 0, 25, 0);//分别是margin_top那四个属性
        LayoutParams lp = new LayoutParams(mp);
        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        Button bt = new Button(this);
        bt.setLayoutParams(lp);
        rl.addView(bt);



        relativeLayout.addView(tv_commodity);
        relativeLayout.addView(tv_money);
        mlllist.addView(relativeLayout, 0);
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

}


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









