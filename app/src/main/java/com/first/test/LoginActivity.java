package com.first.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText mUserName,mPassWord;
    private Button mBtnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mPassWord = findViewById(R.id.password);
        mUserName = findViewById(R.id.username);
        mBtnlogin = findViewById(R.id.btn_login);

        mBtnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,SelectShopActivity.class);
                Toast.makeText(LoginActivity.this,"你的密码是"+mPassWord+" "+"你的帐号是"+mUserName,Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });


    }
}
