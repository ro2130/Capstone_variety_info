package com.example.shingshinginfo;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitAPI {
    //싱싱정보통 브랜치 확인
    @GET("api/variety")
    Call<List<ResponseShingShingInfo>> getInfo();
}
