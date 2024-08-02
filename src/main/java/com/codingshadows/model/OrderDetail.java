package com.codingshadows.model;

import lombok.Data;

@Data
public class OrderDetail {
    private Long id;
    private Long orderId;
    private String productName;
    private int quantity;
    private double price;
}

