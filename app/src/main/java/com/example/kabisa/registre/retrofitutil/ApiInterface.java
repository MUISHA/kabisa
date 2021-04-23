package com.example.kabisa.registre.retrofitutil;

import com.example.kabisa.registre.modeles.ApiResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("registre.php")
    Call<ApiResponse> perfomUserSignIn(
            @Field("name")String name,
            @Field("email")String email,
            @Field("password")String password);

    @FormUrlEncoded
    @POST("login.php")
    Call<ApiResponse> perfomUserLogin(
            @Field("email")String email,
            @Field("password")String password);
}
