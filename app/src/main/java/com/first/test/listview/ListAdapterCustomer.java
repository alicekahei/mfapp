package com.first.test.listview;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.first.test.R;
import com.first.test.webceshi.ResponseCustomerList;
import com.first.test.widget.CircleImageView;
import com.first.test.widget.MyTimeUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

import java.util.List;



public class ListAdapterCustomer extends BaseAdapter {
    private  List<ResponseCustomerList.ValuesCus> mCustomerList;
    private Context mContext;
    public ListAdapterCustomer(Context context, List<ResponseCustomerList.ValuesCus> list){
        mContext = context;
        mCustomerList =list;
    }

    @Override
    public int getCount() {
        return mCustomerList.size();
    }

    @Override
    public Object getItem(int i) {
        return mCustomerList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    static class ViewHolder{
        private TextView mTvSexAge,mTvNew,mTvShoppingRate,mTvShoppingContent,mTvTime;
        private ImageView mIvCustomer;
        private View mVSex;

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
            holder.mVSex = view.findViewById(R.id.v_sex);

            view.setTag(holder);
        }else {
            holder = (ListAdapterCustomer.ViewHolder) view.getTag();
        }

        if (mCustomerList.get(i).getSex()==1){
            holder.mVSex.setBackgroundResource(R.drawable.bg_sex_1);
        }else {
            holder.mVSex.setBackgroundResource(R.drawable.bg_sex_2);
        }
        holder.mTvSexAge.setText(mCustomerList.get(i).getSexDesc()+"   "+mCustomerList.get(i).getAge()+"岁");

        if (mCustomerList.get(i).newbie()){
            holder.mTvNew.setVisibility(View.VISIBLE);
        }
        if (mCustomerList.get(i).getEntryNum()!=0) {
            holder.mTvShoppingRate.setVisibility(View.VISIBLE);
            holder.mTvShoppingRate.setText("来过" + mCustomerList.get(i).getEntryNum() + "次" + "    " + "购物率" + mCustomerList.get(i).getConsumePercentage() + "%");
        }


        if (!mCustomerList.get(i).getItems().isEmpty()){
            for (int j=0;j< mCustomerList.get(i).getItems().size();j++){
                holder.mTvShoppingContent.setVisibility(View.VISIBLE);
                StringBuffer strB = new StringBuffer();
                strB.append(mCustomerList.get(i).getItems().get(j));
                holder.mTvShoppingContent.setText(strB);
            }

        }


        //String转换为java.util.Date
        String str = mCustomerList.get(i).entryTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null; //初始化date
        try {
            date = sdf.parse(str); //Mon Jan 14 00:00:00 CST 2013
        } catch (ParseException e) {
            e.printStackTrace();
        }
        MyTimeUtil myTimeUtil = new MyTimeUtil();
        holder.mTvTime.setText(myTimeUtil.getTimeFormatText(date));



        Glide.with(mContext).load(mCustomerList.get(i).getPic()).into(holder.mIvCustomer);




        return view;
    }


}
