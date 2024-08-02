package com.codingshadows.model;

import lombok.Data;

@Data
public class Order {
    private Long id;
    private Long customerId;
    private String status;
}

