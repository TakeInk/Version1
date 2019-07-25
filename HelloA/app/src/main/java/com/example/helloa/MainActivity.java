package com.example.helloa;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.SharedPreferences;

import java.io.IOException;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    //@Inject SharedPreferences sharedPreferences;
    public String BASE_URL = "https://api.github.com/users/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //assign singleton instances to fields
        //InjectorClass.inject(this);
        //dagger
        ((MyApp) getApplication()).getNetComponent().inject(this);
        //retrofit2 : 标记
        //4.创建 Retrofit 实例
        //关于网络请求适配器（CallAdapter）
        //Retrofit支持多种网络请求适配器方式：guava、Java8和rxjava
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fanyi.youdao.com/") // 设置网络请求的Url地址
                .addConverterFactory(GsonConverterFactory.create()) // 设置数据解析器,多种解析，需要添加依赖
                //.addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 支持RxJava平台 ----------
                .build();

        // 具体使用
        //步骤5：创建网络请求接口实例
        // 创建 网络请求接口 的实例
        GetRequest_Interface request = retrofit.create(GetRequest_Interface.class);
        //对 发送请求 进行封装
        Call<Reception> call = request.getCall();

        // @FormUrlEncoded
        //Call<ResponseBody> call1 = service.testFormUrlEncoded1("Carson", 24);

        //  @Multipart
        /*
        RequestBody name = RequestBody.create(textType, "Carson");
        RequestBody age = RequestBody.create(textType, "24");

        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", "test.txt", file);
        Call<ResponseBody> call3 = service.testFileUpload1(name, age, filePart);
        */
        //发送网络请求(异步)
        call.enqueue(new Callback<Translation>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<Translation> call, Response<Translation> response) {
                //请求处理,输出结果
                //步骤7，处理返回数据
                response.body().show();
            }

            //请求失败时候的回调
            @Override
            public void onFailure(Call<Translation> call, Throwable throwable) {
                System.out.println("连接失败");
            }
        });

// 发送网络请求（同步）
        Response<Reception> response = call.execute();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
