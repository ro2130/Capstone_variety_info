package com.example.shingshinginfo.h_network;



import com.example.shingshinginfo.ResponseShingShingInfo;

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

    //로그인 페이지
    @FormUrlEncoded
    @POST("users/login") //로그인
    Call<String> getLoginCheck(
            @Field("id") String id,
            @Field("pw") String pw
    );
    @GET("{userId}") //로그인 후 회원정보 요청
    Call<com.example.shingshinginfo.h_network.ResponseUserIdent> getUserIdent(@Path("userId") String userId);


    //회원가입 페이지
    @FormUrlEncoded
    @POST("users/search/ID") //아이디 중복 확인
    Call<String> registerIdCheck(
            @Field("id") String id
    );
    @FormUrlEncoded
    @POST("users/search/NickName") //별명 중복 확인
    Call<String> registerNickNameCheck(
            @Field("nickname") String nickname
    );

    @FormUrlEncoded
    @POST("users/signUp") //회원가입 승인
    Call<String> registerLogin(
            @Field("id") String id,
            @Field("pw") String pw,
            @Field("name") String name,
            @Field("nickname") String nickname,
            @Field("email") String email,
            @Field("phone") String phone,
            @Field("key") String key
    );

    //이민영
    @GET("api/variety") //싱싱정보통 요청
    Call<String> getInfo();//String으로 오더라....
   // Call<List<ResponseShingShingInfo>> getInfo();



}
