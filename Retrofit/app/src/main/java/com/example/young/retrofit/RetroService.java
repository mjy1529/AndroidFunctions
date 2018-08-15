package com.example.young.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RetroService {

    @Headers("Content-Type: application/json")
    @POST("/category_info/ko") //요청할 URL
    Call<CategoryList> getCategoryList(@Body String cmd);

    @Headers("Content-Type: application/json")
    @POST("/home_info/ko")
    Call<HomeResult> getHomeResult(@Body String cmd);
}
