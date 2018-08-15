package com.example.young.retrofit;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class MainActivity extends AppCompatActivity {

    Retrofit retrofit;
    String jsonStr;
    TextView textView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        imageView = (ImageView) findViewById(R.id.imageView);

        //Retrofit 객체 생성
        retrofit = new Retrofit.Builder()
                .baseUrl("http://175.123.138.122:3200/") //서버 URL
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()) //gson converter 사용
                .build(); //객체 반환

        jsonToString();
        networking();

    }

    public String jsonToString() {
        try {
            JSONObject data = new JSONObject();
            data.put("cmd", "home_info");

            JSONObject root = new JSONObject();
            root.put("info", data);
            jsonStr = root.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonStr;
    }

//    public void networking() {
//        RetroService service = retrofit.create(RetroService.class); //인터페이스 객체 생성
//        final Call<CategoryList> request = service.getCategoryList(jsonStr.toString());
//        request.enqueue(new Callback<CategoryList>() { //enqueue : 요청 처리
//            @Override
//            public void onResponse(Call<CategoryList> call, Response<CategoryList> response) {
//                if (response.body() != null) {
//                    CategoryList categoryList = response.body();
//                    ArrayList<Category> categories = categoryList.category_info;
//                    textView.setText(categories.get(0).category_title);
//                    String url = categories.get(0).category_image_url;
//
//                    Picasso.with(MainActivity.this).load(url).into(imageView);
//                } else
//                    textView.setText(response.code() + " 에러");
//            }
//
//            @Override
//            public void onFailure(Call<CategoryList> call, Throwable t) {
//                textView.setText("실패");
//            }
//        });
//    }

    public void networking() {
        RetroService service = retrofit.create(RetroService.class);
        Call<HomeResult> request = service.getHomeResult(jsonToString());
        request.enqueue(new Callback<HomeResult>() {
            @Override
            public void onResponse(Call<HomeResult> call, Response<HomeResult> response) {
                Log.d("check", "home 성공 : " + response.code());
                if (response.isSuccessful()) {
                    HomeResult homeResult = response.body();
                    HomeData homeData = homeResult.home_info;
                    textView.setText(homeData.home_title);
                }
            }

            @Override
            public void onFailure(Call<HomeResult> call, Throwable t) {
                Log.d("check", "home 실패");
            }
        });
    }

}
