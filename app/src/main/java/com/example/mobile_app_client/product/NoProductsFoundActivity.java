package com.example.mobile_app_client.product;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mobile_app_client.R;
import com.example.mobile_app_client.ShopFragment;

public class NoProductsFoundActivity extends AppCompatActivity {
    private String categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_no_products_found);

        TextView noProductsPageTitle = findViewById(R.id.noProductsPageTitle);
        ImageButton backButton = findViewById(R.id.backButton);
        Button buttonBack = findViewById(R.id.buttonBack);
        categoryName = getIntent().getStringExtra("categoryName");
        noProductsPageTitle.setText(categoryName);

        // Handle back btn click
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Handle back arrow btn click
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Get the category name from the Intent and set it as the activity title
        String categoryName = getIntent().getStringExtra("categoryName");
        if (categoryName != null) {
            setTitle(categoryName);
        }
    }
}