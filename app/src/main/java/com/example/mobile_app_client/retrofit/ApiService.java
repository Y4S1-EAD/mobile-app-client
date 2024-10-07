package com.example.mobile_app_client.retrofit;

import com.example.mobile_app_client.auth.LoginRequest;
import com.example.mobile_app_client.auth.LoginResponse;
import com.example.mobile_app_client.auth.RegisterRequest;
import com.example.mobile_app_client.auth.RegisterResponse;
import com.example.mobile_app_client.cart.Cart;
import com.example.mobile_app_client.product.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @POST("Users/login")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

    @POST("Users")
    Call<RegisterResponse> registerUser(@Body RegisterRequest registerRequest);

    // Cart APIs
    @GET("Cart/user/{userId}")
    Call<List<Cart>> getCartByUserId(@Path("userId") String userId);

    @DELETE("Cart/user/{userId}")
    Call<Void> clearCartByUserId(@Path("userId") String userId);

    // Product APIs
    @GET("Products/{productId}")
    Call<Product> getProductById(@Path("productId") String productId);
}
