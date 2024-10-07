package com.example.mobile_app_client.retrofit;

import com.example.mobile_app_client.auth.LoginRequest;
import com.example.mobile_app_client.auth.LoginResponse;
import com.example.mobile_app_client.auth.RegisterRequest;
import com.example.mobile_app_client.auth.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("Users/login")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

    @POST("Users")
    Call<RegisterResponse> registerUser(@Body RegisterRequest registerRequest);
}
