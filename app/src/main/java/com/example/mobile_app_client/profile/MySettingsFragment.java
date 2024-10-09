package com.example.mobile_app_client.profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.mobile_app_client.R;

public class MySettingsFragment extends Fragment {
    private Button btnAccDelete;
    private RelativeLayout changePasswordOption;
    private RelativeLayout changeEmailOption;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_my_settings, container, false);

        btnAccDelete = view.findViewById(R.id.btnAccDelete);
        changePasswordOption = view.findViewById(R.id.changePasswordOption);
        changeEmailOption = view.findViewById(R.id.changeEmailOption);

        btnAccDelete.setOnClickListener(v -> {
            // Handle delete account button click
        });

        changePasswordOption.setOnClickListener(v -> {
            FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, new ChangePasswordFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        });

        changeEmailOption.setOnClickListener(v -> {
            FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frame_layout, new ChangeEmailFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        });


        return view;
    }
}