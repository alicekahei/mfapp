package com.first.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.first.test.webceshi.BaseRequest;
import com.first.test.webceshi.BaseResponse;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText mUserName,mPassWord;
    private String UserName, PassWord;
    private Button mBtnlogin;
    final String url = "https:retail-proxy.yipiaoyun.cn/";
    private BaseResponse baseResponse;
    private BaseRequest baseRequest;

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
                // 获取
                UserName = mUserName.getText().toString();
                PassWord = mPassWord.getText().toString();
                //gson
               baseRequest =new BaseRequest(UserName,PassWord);

                Gson gson =new Gson();
                //post请求
                OkHttpClient client = new OkHttpClient();
                RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), gson.toJson(baseRequest));
                final Request request = new Request.Builder()
                        .post(body)
                        .url(url+"app/users/login")
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String str = response.body().string();

                        //使用gson

                        Gson gson =new Gson();
                        baseResponse = gson.fromJson(str, BaseResponse.class);
                        Log.d("woshi",baseResponse.getMessage());
                        if (!baseResponse.getSucc()){

                            //解决在子线程中调用Toast的异常情况处理
                            Looper.prepare();
                            Toast.makeText(LoginActivity.this,baseResponse.getMessage(),Toast.LENGTH_LONG).show();
                            Looper.loop();
                        }

                        if (baseResponse.getSucc()){
                            Intent intent = new Intent(LoginActivity.this,SelectShopActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    }
                });



            }
        });

    }
}
