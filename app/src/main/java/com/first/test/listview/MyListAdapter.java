package com.first.test.listview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.first.test.R;
import com.first.test.webceshi.ResponseShop;
import com.first.test.webceshi.Value;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MyListAdapter extends BaseAdapter {
    private List<ResponseShop.ValueShop> shopName = new LinkedList<>();

    private Context mContext;


    public MyListAdapter(Context context, List<ResponseShop.ValueShop> list) {
        this.mContext = context;
        shopName = list;

    }

    @Override
    public int getCount() {
        return shopName.size();
    }

    @Override
    public Object getItem(int i) {
        return shopName.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    static class ViewHolder {
        private TextView mTvShopName;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {

            holder = new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.layout_shop_list, null);
            holder.mTvShopName = view.findViewById(R.id.tv_shop_name);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }



        holder.mTvShopName.setText(shopName.get(i).getName());


        return view;

    }

}
