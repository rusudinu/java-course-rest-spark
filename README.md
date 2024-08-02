### Problem Scenario

You have been hired to develop a simple backend server for an e-commerce application that manages customers, orders, and order details. The backend should provide a RESTful API allowing for the creation, retrieval, updating, and deletion of customers and orders, as well as the retrieval of order details.

### Requirements:

1. **Customer Management**:
    - The server should allow the creation of a new customer with details such as their `id`, `name`, and `email`.
    - Users should be able to retrieve a specific customer by their ID or list all customers.
    - Users should also be able to delete a customer by their ID.

2. **Order Management**:
    - The server should support creating orders associated with a specific customer.
    - Each order should have an `id`, a `customerId` (indicating the customer who placed the order), and a `status` (e.g., "Processing", "Shipped", "Delivered").
    - Users should be able to retrieve a specific order by its ID, list all orders, or filter orders based on a `customerId`.
    - Orders should be deletable by their ID.

3. **Order Details Management**:
    - Each order can have multiple details, specifying the products in the order.
    - The order details include the `id` of the order detail, the `orderId` it belongs to, the `productName`, the `quantity` of the product, and the `price` of each item.
    - Users should be able to retrieve the order details for a specific order by its `orderId`.

### Implementation:

The backend is implemented using Java with a simple in-memory data storage solution using `HashMap`. It provides the following REST API endpoints:

1. **Customer Endpoints**:
    - `GET /customers/:id` - Retrieves a customer by their ID.
    - `PUT /customers` - Creates or updates a customer.
    - `DELETE /customers/:id` - Deletes a customer by their ID.
    - `GET /customers` - Lists all customers.

2. **Order Endpoints**:
    - `GET /orders/:id` - Retrieves an order by its ID.
    - `PUT /orders` - Creates or updates an order.
    - `DELETE /orders/:id` - Deletes an order by its ID.
    - `GET /orders` - Lists all orders or filters them by `customer_id` if provided.

3. **Order Details Endpoint**:
    - `GET /orderdetails/:orderId` - Retrieves all order details for a given order ID.

You MUST use Java Spark.
