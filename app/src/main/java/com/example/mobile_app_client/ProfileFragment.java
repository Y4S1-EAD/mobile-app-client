// com/example/mobile_app_client/ProfileFragment.java
package com.example.mobile_app_client;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobile_app_client.auth.LoginActivity;
import com.example.mobile_app_client.orderDetails.MyOrdersFragment;

public class ProfileFragment extends Fragment {

    private SharedPreferences sharedPreferences;
    private Button btnLogout;

    private RelativeLayout myOrdersOption;
    // Declare other options if needed
    // private RelativeLayout myReviewsOption;
    // private RelativeLayout settingsOption;

    private TextView userName;
    private TextView userEmail;
    private ImageView profilePicture;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // You can initialize other things here if necessary
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Initialize shared preferences
        sharedPreferences = getActivity().getSharedPreferences("userSession", Context.MODE_PRIVATE);

        // Initialize views
        btnLogout = view.findViewById(R.id.btnLogout);
        myOrdersOption = view.findViewById(R.id.myOrdersOption);
        userName = view.findViewById(R.id.userName);
        userEmail = view.findViewById(R.id.userEmail);
        profilePicture = view.findViewById(R.id.profilePicture);

        // Set user information
        String name = sharedPreferences.getString("username", "Guest User");
        String email = sharedPreferences.getString("email", "No email");
        userName.setText(name);
        userEmail.setText(email);

        // Optionally load profile picture
        // You can use libraries like Glide or Picasso to load images
        // For example:
        // String profilePicUrl = sharedPreferences.getString("profilePictureUrl", null);
        // if (profilePicUrl != null) {
        //     Glide.with(this).load(profilePicUrl).into(profilePicture);
        // }

        // Handle logout button click
        btnLogout.setOnClickListener(v -> {
            // Clear session data
            sharedPreferences.edit().clear().apply();

            // Show a message that the user has logged out
            Toast.makeText(getActivity(), "Logged out successfully", Toast.LENGTH_SHORT).show();

            // Redirect to LoginActivity
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);

            // Finish the current activity to prevent back navigation to it
            getActivity().finish();
        });

        // Set click listener for My Orders
        myOrdersOption.setOnClickListener(v -> {
            // Navigate to MyOrdersFragment
            FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, new MyOrdersFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        });

        // You can initialize and set up other options similarly
        // ...

        return view;
    }
}
