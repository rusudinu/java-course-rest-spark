package com.codingshadows;

import static spark.Spark.awaitInitialization;
import static org.junit.jupiter.api.Assertions.*;
import static spark.Spark.stop;

import com.codingshadows.model.Customer;
import com.codingshadows.model.Order;
import com.google.gson.Gson;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderAppTest {

    private static final Gson gson = new Gson();

    @BeforeAll
    public void setup() {
        OrderApp.main(new String[]{});
        awaitInitialization();
        waitForServerToStart();
    }

    @AfterAll
    public void tearDown() {
        stop();
    }

    @Test
    public void testCreateAndGetCustomer() throws IOException {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("John Doe");
        customer.setEmail("john@example.com");

        // PUT request to create customer
        String putResponse = httpRequest("PUT", "/customers", gson.toJson(customer));
        assertNotNull(putResponse);

        // GET request to retrieve customer by ID
        String getResponse = httpRequest("GET", "/customers/1", null);
        Customer retrievedCustomer = gson.fromJson(getResponse, Customer.class);

        assertEquals(customer.getId(), retrievedCustomer.getId());
        assertEquals(customer.getName(), retrievedCustomer.getName());
        assertEquals(customer.getEmail(), retrievedCustomer.getEmail());
    }

    @Test
    public void testGetAllCustomers() throws IOException {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("John Doe");
        customer.setEmail("john@example.com");

        // PUT request to create customer
        String putResponse = httpRequest("PUT", "/customers", gson.toJson(customer));
        assertNotNull(putResponse);
        // GET request to retrieve all customers
        String getResponse = httpRequest("GET", "/customers", null);
        Customer[] customers = gson.fromJson(getResponse, Customer[].class);

        assertTrue(customers.length > 0);
    }

    @Test
    public void testCreateAndGetOrder() throws IOException {
        Order order = new Order();
        order.setId(1L);
        order.setCustomerId(1L);
        order.setStatus("Pending");

        // PUT request to create order
        String putResponse = httpRequest("PUT", "/orders", gson.toJson(order));
        assertNotNull(putResponse);

        // GET request to retrieve order by ID
        String getResponse = httpRequest("GET", "/orders/1", null);
        Order retrievedOrder = gson.fromJson(getResponse, Order.class);

        assertEquals(order.getId(), retrievedOrder.getId());
        assertEquals(order.getCustomerId(), retrievedOrder.getCustomerId());
        assertEquals(order.getStatus(), retrievedOrder.getStatus());
    }

    private void waitForServerToStart() {
        int maxRetries = 10;
        int retryCount = 0;
        boolean serverStarted = false;

        while (retryCount < maxRetries) {
            try {
                TimeUnit.SECONDS.sleep(1);
                URL url = new URL("http://localhost:4567/customers");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                if (connection.getResponseCode() == 200) {
                    serverStarted = true;
                    break;
                }
            } catch (IOException | InterruptedException e) {
                // Retry
                retryCount++;
            }
        }

        if (!serverStarted) {
            throw new IllegalStateException("Server did not start after " + maxRetries + " retries.");
        }
    }

    private String httpRequest(String method, String path, String body) throws IOException {
        URL url = new URL("http://localhost:4567" + path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        connection.setRequestProperty("Content-Type", "application/json");

        if (body != null) {
            connection.setDoOutput(true);
            connection.getOutputStream().write(body.getBytes("UTF-8"));
        }

        connection.connect();
        Scanner scanner = new Scanner(connection.getInputStream());
        String response = scanner.useDelimiter("\\A").next();
        scanner.close();
        return response;
    }
}
