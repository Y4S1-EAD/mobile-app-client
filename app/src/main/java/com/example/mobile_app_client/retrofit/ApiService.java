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
import com.example.mobile_app_client.reviews.RatingResponse;
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
    Call<User> updateUserPassword(@Path("userId") String userId, @Body List<UpdateOperation> operations);

    @PATCH("Users/{userId}")
    Call<User> updateUserEmail(@Path("userId") String userId, @Body List<UpdateOperation> operations);

    @DELETE("Users/{userId}")
    Call<Void> deleteUserById(@Path("userId") String userId);


    @GET("Rating/user/{userId}")
    Call<List<RatingResponse>> getUserRatings(@Path("userId") String userId);

    @GET("Users/{vendorId}")
    Call<User> getVendorById(@Path("vendorId") String vendorId);

    @PATCH("Rating/{ratingId}")
    Call<Void> updateRating(@Path("ratingId") String ratingId, @Body List<UpdateOperation> operations);


}

