package com.example.mobile_app_client.reviews;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mobile_app_client.R;
import com.example.mobile_app_client.retrofit.ApiService;
import com.example.mobile_app_client.retrofit.RetrofitClient;
import com.example.mobile_app_client.user.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class MyReviewsFragment extends Fragment {

    private RecyclerView reviewsRecyclerView;
    private ReviewAdapter reviewAdapter;
    private List<Review> reviewsList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_reviews, container, false);

        // Initialize RecyclerView
        reviewsRecyclerView = view.findViewById(R.id.reviewsRecyclerView);
        reviewsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        reviewsList = new ArrayList<>();
        reviewAdapter = new ReviewAdapter(reviewsList);
        reviewsRecyclerView.setAdapter(reviewAdapter);

        // Fetch userId from SharedPreferences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("userSession", Context.MODE_PRIVATE);
        String userId = sharedPreferences.getString("userId", "");

        // Fetch user reviews
        fetchUserReviews(userId);

        return view;
    }

    private void fetchUserReviews(String userId) {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);

        Call<List<RatingResponse>> call = apiService.getUserRatings(userId);
        call.enqueue(new Callback<List<RatingResponse>>() {
            @Override
            public void onResponse(Call<List<RatingResponse>> call, Response<List<RatingResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<RatingResponse> ratings = response.body();
                    for (RatingResponse rating : ratings) {
                        // Fetch vendor name for each rating
                        fetchVendorName(rating);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<RatingResponse>> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to fetch reviews", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchVendorName(RatingResponse rating) {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);

        Call<User> call = apiService.getVendorById(rating.getVendorId());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String vendorName = response.body().getUsername();
                    Review review = new Review(vendorName, rating.getRatingValue(), rating.getComment(), rating.getDatePosted());
                    reviewsList.add(review);
                    reviewAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to fetch vendor name", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
