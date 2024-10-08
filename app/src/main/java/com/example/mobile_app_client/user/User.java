package com.example.mobile_app_client.user;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("userId")
    private String userId;

    @SerializedName("username")
    private String username;

    @SerializedName("phoneNumber")
    private String phoneNumber;

    @SerializedName("email")
    private String email;

    @SerializedName("address")
    private String address;

    // Other fields as needed...

    // Getters and Setters
    public String getUserId() { return userId; }
    public String getUsername() { return username; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber;}

    // Add setters for other fields if needed
}
