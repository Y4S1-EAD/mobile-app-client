package com.example.mobile_app_client.retrofit;

import com.example.mobile_app_client.notification.Notification;
import com.example.mobile_app_client.auth.LoginRequest;
import com.example.mobile_app_client.auth.LoginResponse;
import com.example.mobile_app_client.auth.RegisterRequest;
import com.example.mobile_app_client.auth.RegisterResponse;
import com.example.mobile_app_client.cart.Cart;
import com.example.mobile_app_client.order.Order;
import com.example.mobile_app_client.payment.Payment;
import com.example.mobile_app_client.product.Product;
import com.example.mobile_app_client.profile.PasswordUpdateOperation;
import com.example.mobile_app_client.user.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
    @POST("Users/login")
    Call<LoginResponse> loginUser(@Body LoginRequest loginRequest);

    @POST("Users")
    Call<RegisterResponse> registerUser(@Body RegisterRequest registerRequest);

    // User APIs
    @GET("Users/{userId}")
    Call<User> getUserById(@Path("userId") String userId);

    // Cart APIs
    @GET("Cart/user/{userId}")
    Call<List<Cart>> getCartByUserId(@Path("userId") String userId);

    @DELETE("Cart/user/{userId}")
    Call<Void> clearCartByUserId(@Path("userId") String userId);

    @GET("Orders/{orderId}")
    Call<Order> getOrderById(@Path("orderId") String orderId);

    // Product APIs
    @GET("Products/{productId}")
    Call<Product> getProductById(@Path("productId") String productId);

    @PUT("Products/{productId}")
    Call<Product> updateProduct(@Path("productId") String productId, @Body Product product);

    @POST("Orders")
    Call<Order> createOrder(@Body Order order);

    @GET("Orders/user/{userId}")
    Call<List<Order>> getOrdersByUserId(@Path("userId") String userId);

    @POST("Payments")
    Call<Payment> createPayment(@Body Payment payment);

    @POST("Notification")
    Call<Notification> createNotification(@Body Notification notification);

    @PATCH("Users/{userId}")
    Call<User> updateUserPassword(@Path("userId") String userId, @Body List<PasswordUpdateOperation> operations);

}
