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
        function updateDeliveryOptions() {
            const deliveryOption = document.querySelector('input[name="deliveryOption"]:checked');
            const deliverySection = document.getElementById("deliverySection");
            const deliveryFeeElement = document.getElementById("deliveryFee");
            const totalPriceElement = document.getElementById("totalPrice");
            const hiddenDeliveryFee = document.getElementById("hiddenDeliveryFee");

            // Define the delivery fee
            const deliveryFee = 5.99;
            const subtotal = parseFloat(cart.get_price());

            let finalDeliveryFee = 0;

            if (deliveryOption && deliveryOption.value === 'delivery') {
                deliverySection.style.display = "block";
                finalDeliveryFee = deliveryFee;
            } else {
                deliverySection.style.display = "none";
                finalDeliveryFee = 0;
            }

            // Update the delivery fee and total price on the page
            deliveryFeeElement.innerText = "$" + finalDeliveryFee.toFixed(2);
            totalPriceElement.innerText = "$" + (subtotal + finalDeliveryFee).toFixed(2);
            hiddenDeliveryFee.value = finalDeliveryFee;
        }

        document.addEventListener("DOMContentLoaded", function() {
            const deliveryOptions = document.querySelectorAll('input[name="deliveryOption"]');
            deliveryOptions.forEach(option => {
                option.addEventListener("change", updateDeliveryOptions);
            });

            // Set initial values on page load
            updateDeliveryOptions();
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

    <form action="payForOrder.jsp" method="post">
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

        <!-- Delivery Details Section (Hidden by default - unless delivery selected) -->
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

        <!-- Hidden Input to Store Delivery Fee -->
        <input type="hidden" id="hiddenDeliveryFee" name="deliveryFee" value="0.00">

        <!-- Display subtotal and total price -->
        <h2>Order Summary</h2>
        <table>
            <tr>
                <td>Subtotal</td>
                <td>$<%= String.format("%.2f", cart.get_price()) %></td>
            </tr>
            <tr>
                <td>Delivery Fee</td>
                <td id="deliveryFee">$</td>
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
            <button type="submit" class="button">Proceed to Payment</button>
        </div>
    </form>
    <%
            } // End of cart check
        } // End of user check
    %>
</body>
</html>
