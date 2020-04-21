package com.first.test.webceshi;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class WebCeShi extends AppCompatActivity {



    public static void main(String[] args) {
        final String url = "https:retail-proxy.yipiaoyun.cn/";
        //get请求
////        OkHttpClient client = new OkHttpClient();
////        //创建一个request
////        final Request request = new Request.Builder()
////                .get()
////                .url(url+"app/users/login")
////                .build();
//        //通过client发起请求
////        client.newCall(request).enqueue(new Callback() {
////            @Override
////            public void onFailure(Call call, IOException e) {
////
////            }
////
////            @Override
////            public void onResponse(Call call, Response response) throws IOException {
////                if (response.isSuccessful()) {
////                     String str = response.body().string();
////                     System.out.println(str);
////                }
////            }
////        });
//        //get请求2
////            OkHttpClient client = new OkHttpClient();
////            Request request =new Request.Builder()
////                    .get()
////                    .url("https:www.baidu.com")
////                    .build();
////            Call call = client.newCall(request);
////            call.enqueue(new Callback() {
////                @Override
////                public void onFailure(Call call, IOException e) {
////                    //   Toast.makeText(WebCeShi.this, "get failed", Toast.LENGTH_SHORT).show();
////                }
////
////                @Override
////                public void onResponse(Call call, final Response response) throws IOException {
////                    final String res = response.body().string();
////
////
////                            System.out.println(res);
////
////
////                }
////            });
//
//
//        //post请求
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), "{\"nick\":\"jinfang\",\"password\":\"123456\"}");
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
                System.out.println(str);
                Gson gson =new Gson();
                BaseResponse baseRequest = gson.fromJson(str, BaseResponse.class);

                System.out.println(baseRequest);
                System.out.println(baseRequest.getValue().getToken());
            }
        });
//
//
//        //post方法2
////        OkHttpClient client = new OkHttpClient();
////        FormBody formBody = new FormBody.Builder()
////                .add("nick", "jinfang")
////                .add("password", "123456")
////                .build();
////        final Request request = new Request.Builder()
////                .url(url+"app/users/login")
////                .post(formBody)
////                .build();
////        Call call = client.newCall(request);
////        call.enqueue(new Callback() {
////            @Override
////            public void onFailure(Call call, IOException e) {
////                System.out.println("fail");
////            }
////
////            @Override
////            public void onResponse(Call call, Response response) throws IOException {
////                final String res = response.body().string();
////                System.out.println(res);
////            }
////        });
//        //post方法3
//
//        //ceshi gson
//
//
//
   }

}




