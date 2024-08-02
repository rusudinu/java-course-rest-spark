package com.codingshadows;

import static spark.Spark.*;

import com.codingshadows.model.Customer;
import com.codingshadows.model.Order;
import com.codingshadows.model.OrderDetail;
import com.google.gson.Gson;

import java.util.*;

public class OrderApp {
    private static Map<Long, Customer> customers = new HashMap<>();
    private static Map<Long, Order> orders = new HashMap<>();
    private static Map<Long, List<OrderDetail>> orderDetails = new HashMap<>();
    private static Gson gson = new Gson();

    public static void main(String[] args) {
        System.out.println("starting server");
        port(4567);

        // Customer endpoints
        get("/customers/:id", (req, res) -> {
            // TODO - implement this endpoint according to the specifications in README
        });

        put("/customers", (req, res) -> {
            Customer customer = gson.fromJson(req.body(), Customer.class);
            // TODO - implement this endpoint according to the specifications in README
            return gson.toJson(customer);
        });

        delete("/customers/:id", (req, res) -> {
            // TODO - implement this endpoint according to the specifications in README
        });

        get("/customers", (req, res) -> gson.toJson(customers.values()));

        // Order endpoints
        get("/orders/:id", (req, res) -> {
            // TODO - implement this endpoint according to the specifications in README
        });

        put("/orders", (req, res) -> {
            // TODO - implement this endpoint according to the specifications in README
        });

        delete("/orders/:id", (req, res) -> {
            // TODO - implement this endpoint according to the specifications in README
        });

        get("/orders", (req, res) -> {
            String customerIdParam = req.queryParams("customer_id");
            if (customerIdParam != null) {
                // TODO - implement this endpoint according to the specifications in README
                return gson.toJson(customerOrders);
            }
            return gson.toJson(orders.values());
        });

        // OrderDetails endpoints
        get("/orderdetails/:orderId", (req, res) -> {
            long orderId = Long.parseLong(req.params(":orderId"));
            // TODO - implement this endpoint according to the specifications in README
            return gson.toJson(details);
        });
    }
}
