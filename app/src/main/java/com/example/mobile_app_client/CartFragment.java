package com.example.mobile_app_client;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;

import com.example.mobile_app_client.cart.Cart;
import com.example.mobile_app_client.cart.CartAdapter;
import com.example.mobile_app_client.cart.CartItem;
import com.example.mobile_app_client.product.Product;
import com.example.mobile_app_client.retrofit.ApiService;
import com.example.mobile_app_client.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Fragment representing the cart.
 */
public class CartFragment extends Fragment {

    private RecyclerView rvCartItems;
    private CartAdapter cartAdapter;
    private List<CartItem> cartItemList;
    private TextView tvTotalAmount;
    private Button buttonCheckout;
    private double totalAmount = 0;
    private String userId;

    private ApiService apiService;

    public CartFragment() {
        // Required empty public constructor
    }

    public static CartFragment newInstance() {
        CartFragment fragment = new CartFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get userId from SharedPreferences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("userSession", Context.MODE_PRIVATE);
        userId = sharedPreferences.getString("userId", null);
        if (userId == null) {
            // Handle the case when userId is not available
            Toast.makeText(getActivity(), "User not logged in", Toast.LENGTH_SHORT).show();
            // Optionally, navigate to LoginActivity
        }

        apiService = RetrofitClient.getClient().create(ApiService.class);

        cartItemList = new ArrayList<>();
        cartAdapter = new CartAdapter(cartItemList, getActivity(), this::updateTotalAmount);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        rvCartItems = view.findViewById(R.id.rvCartItems);
        tvTotalAmount = view.findViewById(R.id.tvTotalAmount);
        buttonCheckout = view.findViewById(R.id.btnCheckout);

        rvCartItems.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvCartItems.setAdapter(cartAdapter);

        fetchCartData();

        buttonCheckout.setOnClickListener(v -> {
            if (totalAmount > 0) {
                // Handle checkout action
                Toast.makeText(getActivity(), "Proceeding to checkout.", Toast.LENGTH_SHORT).show();
                // Implement checkout logic here
            } else {
                Toast.makeText(getActivity(), "Your cart is empty.", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void fetchCartData() {
        Call<List<Cart>> call = apiService.getCartByUserId(userId);
        call.enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                if (response.isSuccessful()) {
                    List<Cart> carts = response.body();
                    if (carts != null && !carts.isEmpty()) {
                        Cart cart = carts.get(0); // Assuming one cart per user
                        if (cart.getProductIds() != null && !cart.getProductIds().isEmpty()) {
                            fetchProducts(cart.getProductIds());
                        } else {
                            Toast.makeText(getActivity(), "Your cart is empty", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getActivity(), "Your cart is empty", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    int statusCode = response.code();
                    String errorMessage = "Failed to load cart. HTTP Status Code: " + statusCode;
                    Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Cart>> call, Throwable t) {
                Toast.makeText(getActivity(), "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
                t.printStackTrace();
            }
        });
    }

    private void fetchProducts(List<String> productIds) {
        cartItemList.clear();
        for (String productId : productIds) {
            Call<Product> call = apiService.getProductById(productId);
            call.enqueue(new Callback<Product>() {
                @Override
                public void onResponse(Call<Product> call, Response<Product> response) {
                    if (response.isSuccessful()) {
                        Product product = response.body();
                        if (product != null) {
                            CartItem cartItem = new CartItem(product, 1); // Assuming quantity is 1
                            cartItemList.add(cartItem);
                            cartAdapter.notifyDataSetChanged();
                            updateTotalAmount();
                        }
                    } else {
                        Toast.makeText(getActivity(), "Failed to load product details", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Product> call, Throwable t) {
                    Toast.makeText(getActivity(), "Error: " + t.getMessage(), Toast.LENGTH_LONG).show();
                    t.printStackTrace();
                }
            });
        }
    }

    private void updateTotalAmount() {
        totalAmount = 0;
        for (CartItem item : cartItemList) {
            totalAmount += item.getProduct().getPrice() * item.getQuantity();
        }
        tvTotalAmount.setText(String.format("Rs %.2f", totalAmount));
    }
}
