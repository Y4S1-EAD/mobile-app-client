package com.example.mobile_app_client;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.mobile_app_client.auth.LoginActivity;

public class ProfileFragment extends Fragment {

    private SharedPreferences sharedPreferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // You can initialize other things here if necessary
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Initialize shared preferences
        sharedPreferences = getActivity().getSharedPreferences("userSession", getActivity().MODE_PRIVATE);

        // Find the logout button in the layout
        Button btnLogout = view.findViewById(R.id.btnLogout);

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

        return view;
    }
}
