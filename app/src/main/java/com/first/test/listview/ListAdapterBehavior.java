package com.first.test.listview;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.first.test.R;
import com.first.test.webceshi.ResponseCustomerList;
import com.first.test.widget.MyTimeUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class ListAdapterBehavior extends BaseAdapter {
    private Context mContext;
    public ListAdapterBehavior(Context context){
        this.mContext= context;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    static class ViewHolder{
        private TextView mTvData,mTvTime,mTvThisShop,mTvOtherShop,mTvTotalTime,mTvIsPay;
        private ImageView mIvIconShop;
        private View mVShop;
        private RelativeLayout mRlOreder,mRlOreder1;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ListAdapterBehavior.ViewHolder holder = null;
        if (view == null) {

            holder = new ListAdapterBehavior.ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.layout_behavior_item, null);

            holder.mTvData = view.findViewById(R.id.tv_data);
            holder.mTvTime = view.findViewById(R.id.tv_time);
            holder.mTvThisShop = view.findViewById(R.id.tv_this_shop);
            holder.mTvOtherShop = view.findViewById(R.id.tv_other_shop);
            holder.mIvIconShop = view.findViewById(R.id.iv_icon_shop);
            holder.mTvTotalTime = view.findViewById(R.id.tv_total_time);
            holder.mTvIsPay = view.findViewById(R.id.tv_is_pay);
            holder.mVShop =view.findViewById(R.id.v_shop);
            holder.mRlOreder =view.findViewById(R.id.rl_order);
            holder.mRlOreder1 =view.findViewById(R.id.rl_order_1);

            view.setTag(holder);
        } else {
            holder = (ListAdapterBehavior.ViewHolder) view.getTag();
        }

        if (i == 0){

            holder.mTvData.setText("今天");
            holder.mTvTime.setText("12:47:07");
            holder.mTvThisShop.setVisibility(View.VISIBLE);
            holder.mTvOtherShop.setVisibility(View.GONE);
            holder.mIvIconShop.setImageResource(R.mipmap.icon_shop_0);
            holder.mTvTotalTime.setText("5min");
            holder.mTvIsPay.setText("进店·未消费");
            holder.mTvIsPay.setTextColor(Color.parseColor("#DCC9B5"));
            holder.mVShop.setBackgroundColor(Color.parseColor("#DCC9B5"));
            holder.mRlOreder.setVisibility(View.GONE);
            holder.mRlOreder1.setVisibility(View.GONE);
        }else if (i ==1){
            holder.mTvData.setText("昨天");
            holder.mTvTime.setText("12:47:07");
            holder.mTvThisShop.setVisibility(View.VISIBLE);
            holder.mIvIconShop.setImageResource(R.mipmap.icon_shop_1);
            holder.mTvTotalTime.setText("5min");
            holder.mTvIsPay.setText("进店·消费");
            holder.mTvIsPay.setTextColor(Color.parseColor("#6FC594"));
            holder.mVShop.setBackgroundColor(Color.parseColor("#6FC594"));
            holder.mRlOreder.setVisibility(View.VISIBLE);
            holder.mRlOreder1.setVisibility(View.VISIBLE);

        }else if (i ==2){
            holder.mTvData.setText("前天");
            holder.mTvTime.setText("12:47:07");
            holder.mTvOtherShop.setVisibility(View.VISIBLE);
            holder.mIvIconShop.setImageResource(R.mipmap.icon_shop_0);
            holder.mTvTotalTime.setText("5min");
            holder.mTvIsPay.setText("进店·未消费");
        }else if (i ==3){
            holder.mTvData.setText("21日");
            holder.mTvTime.setText("12:47:07");
            holder.mTvOtherShop.setVisibility(View.VISIBLE);
            holder.mIvIconShop.setImageResource(R.mipmap.icon_shop_1);
            holder.mTvTotalTime.setText("5min");
            holder.mTvIsPay.setText("进店·消费");
            holder.mRlOreder.setVisibility(View.VISIBLE);
            holder.mRlOreder1.setVisibility(View.VISIBLE);
            holder.mTvIsPay.setTextColor(Color.parseColor("#6FC594"));
            holder.mVShop.setBackgroundColor(Color.parseColor("#6FC594"));
        }


        return view;
    }
}
