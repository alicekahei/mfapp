package com.first.test.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.first.test.R;

import java.util.Random;

public class CreateFragment extends Fragment {
    private LinearLayout mLlcontent;

    private LinearLayout mLLCreata;
    private TextView mTvadd;
    @Nullable
    @Override
    //返回视图文件
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_create,container,false);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mLlcontent = view.findViewById(R.id.content);

        //添加view
        mTvadd =view.findViewById(R.id.tv_add);
        mTvadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addView(view);
            }
        });


    }
    /**
     * 按钮点击事件，向容器中添加TextView
     *
     * @param view
     */
    public void addView(View view) {
        mLLCreata = view.findViewById(R.id.ll_create_order);
        TextView child = new TextView(getContext());
        child.setTextSize(20);
        child.setTextColor(getResources().getColor(R.color.colorAccent));
        // 获取当前的时间并转换为时间戳格式, 并设置给TextView
        String[] list = {"aaaa", "bbbb", "cccc", "dddd", "ccccc"};
        Random rand = new Random(25);
        int i;
        i = rand.nextInt(5);
        child.setText(list[i]);
        // 调用一个参数的addView方法
        mLLCreata.addView(child);
    }


}
