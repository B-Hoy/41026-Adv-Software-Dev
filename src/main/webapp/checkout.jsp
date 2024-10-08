<%@ page import="uts.advsoft.User" %>
<%@ page import="uts.advsoft.Cart" %>
<%@ page import="uts.advsoft.MenuItemEntry" %>
<%@ page import="uts.advsoft.Database" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="style.css" type="text/css">
    <title>Checkout</title>
    <script>
        function toggleDeliverySection() {
            const deliveryOption = document.querySelector('input[name="deliveryOption"]:checked').value;
            const deliverySection = document.getElementById("deliverySection");
            
            if (deliveryOption === 'delivery') {
                deliverySection.style.display = "block";  // Show delivery details section
            } else {
                deliverySection.style.display = "none";  // Hide delivery details section
            }
        }

        document.addEventListener("DOMContentLoaded", function() {
            const deliveryOptions = document.querySelectorAll('input[name="deliveryOption"]');
            deliveryOptions.forEach(option => {
                option.addEventListener("change", toggleDeliverySection);
            });
        });
    </script>
</head>
<body>
    <h1>Checkout</h1>

    <%
        // Fetch current user from session
        User user = (User) session.getAttribute("user");
        if (user == null) {
            out.println("<p>No user is logged in.</p>");
        } else {
            // Fetch the database and the user's cart
            Database db = (Database) application.getAttribute("database");
            Cart cart = db.get_cart(user.get_id(), "owner_id");

            if (cart == null) {
                out.println("<p>Your cart is empty.</p>");
            } else {
                MenuItemEntry[] cartItems = cart.get_cart_items();
    %>

    <!-- Display cart items in table -->
    <h2>Your Order</h2>
    <table>
        <thead>
            <tr>
                <th>Item</th>
                <th>Quantity</th>
                <th>Price</th>
            </tr>
        </thead>
        <tbody>
            <% for (MenuItemEntry item : cartItems) { %>
                <tr>
                    <td><%= item.get_item().getName() %></td>
                    <td><%= item.get_amount() %></td>
                    <td>$<%= String.format("%.2f", item.get_item().getPrice()) %></td>
                </tr>
            <% } %>
        </tbody>
    </table>

    <!-- Delivery/Pickup Options -->
    <h2>Select Delivery Option</h2>
    <label>
        <input type="radio" name="deliveryOption" value="pickup" required>
        Pick Up
    </label>
    <br>
    <label>
        <input type="radio" name="deliveryOption" value="delivery" required>
        Delivery
    </label>

    <!-- Delivery Details (Hidden by default - unless delivery selected) -->
    <div id="deliverySection" style="display:none; margin-top: 20px;">
        <h3>Delivery Information</h3>
        <label for="streetNumber">Street Number:</label>
        <input type="text" id="streetNumber" name="streetNumber" value="<%= user.get_address_street_num() %>" required>
        <br>

        <label for="streetAddress">Street Address:</label>
        <input type="text" id="streetAddress" name="streetAddress" value="<%= user.get_address_street() %>" required>
        <br>

        <label for="city">Suburb/City:</label>
        <input type="text" id="city" name="city" value="<%= user.get_address_city() %>" required>
        <br>

        <label for="postcode">Postcode:</label>
        <input type="text" id="postcode" name="postcode" value="<%= user.get_address_postcode() %>" pattern="[0-9]{4}" required>
        <small>Format: 4 digits</small>
        <br><br>
    </div>

    <!-- Display subtotal -->
    <h2>Order Summary</h2>
    <table>
        <tr>
            <td>Subtotal</td>
            <td>$<%= String.format("%.2f", cart.get_price()) %></td>
        </tr>
        <tr>
            <td>Delivery Fee</td>
            <td id="deliveryFee">$0.00</td> <!-- Will update based on delivery option -->
        </tr>
        <tr>
            <td>Total Price</td>
            <td id="totalPrice">$<%= String.format("%.2f", cart.get_price()) %></td>
        </tr>
    </table>

    <!-- Navigation Buttons -->
    <div style="margin-top: 20px;">
        <a href="landing.jsp" class="button">Home Page</a>
        <a href="menu.jsp" class="button">Continue Ordering</a>
        <button type="submit" formaction="payForOrder.jsp" class="button">Proceed to Payment</button>
    </div>

    <%
            } // End of cart check
        } // End of user check
    %>
</body>
</html>

