package com.example.mobile_app_client.order;

import com.google.gson.annotations.SerializedName;

public class CreateOrderRequest {
    @SerializedName("newOrder")
    private Order newOrder;

    public CreateOrderRequest(Order newOrder) {
        this.newOrder = newOrder;
    }

    public Order getNewOrder() {
        return newOrder;
    }

    public void setNewOrder(Order newOrder) {
        this.newOrder = newOrder;
    }
}
