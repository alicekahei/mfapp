package com.first.test.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.first.test.R;

public class CreateSuccActivity extends AppCompatActivity {

    private TextView mTvCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_succ);

        mTvCreate = findViewById(R.id.tv_create);

        //继续创建
        mTvCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreateSuccActivity.this,CreateOrderActivity.class);
                startActivity(intent);
            }
        });


    }
}
