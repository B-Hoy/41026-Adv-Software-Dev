<%@ page import="java.util.List"%>
<%@ page import="java.util.Arrays"%>
<%@page import="uts.advsoft.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>




<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css" type="text/css">
        <title>Checkout</title>

        <script>
            function updateDeliveryFee() {
                const deliveryOption = document.querySelector('input[name="deliveryOption"]:checked').value;
                let deliveryFee = 0;
                const subtotal = 0.00;  // Can be adjusted (Dummy Data)
                if (deliveryOption === 'delivery') {
                    deliveryFee = 3.95;
                } else {
                    deliveryFee = 0;
                }

                document.getElementById("deliveryFee").innerText = "$" + deliveryFee.toFixed(2);
                document.getElementById("totalPrice").innerText = "$" + (subtotal + deliveryFee).toFixed(2);
            }

            // Add an event listener - updates fee based on selection of delivery option
            document.addEventListener("DOMContentLoaded", function() {
                const deliveryOptions = document.querySelectorAll('input[name="deliveryOption"]');
                deliveryOptions.forEach(option => {
                    option.addEventListener("change", updateDeliveryFee);
                });
            });
        </script>

    </head>
    <body>
        <h1>Checkout</h1>

        <h2>Select Delivery Option</h2>
        <form action="submitOrder.jsp" method="post">
            <label>
                <input type="radio" name="deliveryOption" value="pickup" required>
                Pick Up
            </label>
            <br>
            <label>
                <input type="radio" name="deliveryOption" value="delivery" required>
                Delivery
            </label>

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
                    <tr>
                        <td>Item 1</td>
                        <td><input type="number" name="quantity1" value="1" required></td>
                        <td>$0.00</td>
                    </tr>
                    <tr>
                        <td>Item 2</td>
                        <td><input type="number" name="quantity2" value="1" required></td>
                        <td>$0.00</td>
                    </tr>
                    <tr>
                        <td>Item 3</td>
                        <td><input type="number" name="quantity3" value="1" required></td>
                        <td>$0.00</td>
                    </tr>
                </tbody>
            </table>

            <h2>Your Details</h2>
            <%
                User user = (User) session.getAttribute("user");
                if (user != null) {
            %>
            <div>
                <label>First Name & Last Name:</label>
                <input type="text" name="firstLastName" value="<%= user.get_first_name() + ' ' + user.get_last_name() %>" required>
                <br>
                
                <label>Email Address:</label>
                <input type="email" name="email" value="<%= user.get_email() %>" required>
                <br>
                
                <label>Phone Number:</label>
                <input type="text" name="phone" value="<%= user.get_phone_num() %>" pattern="[0-9]{10}" required>
                <small>Format: 1234567890</small>
                <br><br>

                <label>Street Address:</label>
                <input type="text" name="streetAddress" value="<%= user.get_address_street_num() + ' ' + user.get_address_street() %>" required>
                <br>

                <label>City:</label>
                <input type="text" name="city" value="<%= user.get_address_city() %>" required>
                <br>

                <label>Postcode:</label>
                <input type="text" name="postcode" value="<%= user.get_address_postcode() %>" pattern="[0-9]{4}" required>
                <small>Format: 4 digits</small>
                <br><br>

                <label>Card Number:</label>
                <input type="text" name="cardNumber" value="<%= user.get_card_num() %>" pattern="[0-9]{16}" required>
                <small>Format: 16 digits</small>
                <br>

                <label>Card Name:</label>
                <input type="text" name="cardName" value="<%= user.get_first_name() + ' ' + user.get_last_name() %>" required>
                <br>

                <label>Expiry Date:</label>
                <input type="date" name="expiryDate" value="<%= user.get_card_expiry_date() %>" required>
                <br>

                <label>CVC Code:</label>
                <input type="password" name="cvc" placeholder="Enter CVC" pattern="[0-9]{3}" required>
                <small>Format: 3 digits</small>
            </div>
            <% 
                } else {
                    out.println("<p>No user data available.</p>");
                }
            %>

            <!-- Subtotals Table -->
            <h2>Order Summary</h2>
            <table>
                <tr>
                    <td>Subtotal</td>
                    <td>$0.00</td> <!-- Can be adjusted -->
                </tr>
                <tr>
                    <td>Delivery Fee</td>
                    <td id="deliveryFee">$0.00</td> <!-- Will update based on delivery option -->
                </tr>
                <tr>
                    <td>Total Price</td>
                    <td id="totalPrice">$0.00</td> <!-- Will update based on delivery option -->
                </tr>
            </table>

            <!-- Buttons: Page Navigation -->
            <div style="margin-top: 20px;">
                <a href="landing.jsp" class="button">Homepage</a>
                <a href="menu.jsp" class="button">Continue Ordering</a>
                <button type="submit" class="button">Submit Order</button>
            </div>

        </form>
    </body>
</html>