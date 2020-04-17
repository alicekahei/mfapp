package com.first.test.listview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.first.test.R;

import java.util.ArrayList;
import java.util.List;

public class MyListAdapter extends BaseAdapter {
    private List<String> shopName = new ArrayList<>();

    private Context mContext;


    public MyListAdapter(Context context, List<String> list) {
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

        holder.mTvShopName.setText(shopName.get(i));


        return view;

    }

}
