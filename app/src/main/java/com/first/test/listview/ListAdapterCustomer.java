package com.first.test.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.first.test.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class ListAdapterCustomer extends BaseAdapter {

    private Context mContext;
    public ListAdapterCustomer(Context context){
        mContext = context;
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
    static class ViewHolder{
        private TextView mTvSexAge,mTvNew,mTvShoppingRate,mTvShoppingContent,mTvTime;
        private ImageView mIvCustomer;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ListAdapterCustomer.ViewHolder holder= null;
        if (view == null) {

            holder = new ListAdapterCustomer.ViewHolder();

            view = LayoutInflater.from(mContext).inflate(R.layout.layout_shop_customer_item,null);
            holder.mTvSexAge = view.findViewById(R.id.tv_sex_age);
            holder.mTvNew = view.findViewById(R.id.tv_new);
            holder.mTvShoppingRate = view.findViewById(R.id.tv_times_shopping_rate);
            holder.mTvShoppingContent = view.findViewById(R.id.tv_shopping_content);
            holder.mTvTime = view.findViewById(R.id.tv_time);
            holder.mIvCustomer = view.findViewById(R.id.iv_customer);

            view.setTag(holder);
        }else {
            holder = (ListAdapterCustomer.ViewHolder) view.getTag();
        }
        holder.mTvSexAge.setText("男   12岁");
        holder.mTvNew.isShown();
        holder.mTvShoppingRate.setText("80次   购物率20%");
        holder.mTvShoppingContent.setText("哇哈哈/薯片");
        holder.mTvTime.setText("1分钟前");
        holder.mIvCustomer.setImageResource(R.mipmap.icon_logo);



        return view;
    }


}
