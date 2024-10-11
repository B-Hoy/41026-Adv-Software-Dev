<%@ page import="uts.advsoft.User" %>
<%@ page import="uts.advsoft.Cart" %>
<%@ page import="uts.advsoft.Database" %>
<%@ page import="uts.advsoft.MenuItemEntry" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="style.css" type="text/css">
    <title>Payment Details</title>
</head>
<body>
    <h1>Payment Details</h1>

    <%
        // Fetch the current user and their cart from the session and database
        Database db = (Database) application.getAttribute("database");
        User user = db.get_user("testing@test.com", "testpasswd");
        if (user == null) {
            out.println("<p>No user is logged in.</p>");
        } else {
            Cart cart = db.get_cart(user.get_id(), "owner_id");

            if (cart == null) {
                out.println("<p>Your cart is empty.</p>");
            } else {
                // Calculate subtotal and delivery fee based on selection from checkout.jsp
                String deliveryMethod = request.getParameter("deliveryOption");
                double deliveryFee = deliveryMethod.equals("delivery") ? 5.99 : 0.00;
                double totalPrice = cart.get_price() + deliveryFee;
    %>

    <!-- Display Order Summary -->
    <h2>Order Summary</h2>
    <table>
        <tr>
            <td>Subtotal</td>
            <td>$<%= String.format("%.2f", cart.get_price()) %></td>
        </tr>
        <tr>
            <td>Delivery Fee</td>
            <td>$<%= String.format("%.2f", deliveryFee) %></td>
        </tr>
        <tr>
            <td>Total Price</td>
            <td>$<%= String.format("%.2f", totalPrice) %></td>
        </tr>
    </table>

    <!-- Payment Details Form -->
    <form action="payForOrder.jsp" method="post">
        <h2>Payment Details</h2>
        <label for="cardName">Card Name:</label>
        <input type="text" id="cardName" name="cardName" required><br>

        <label for="cardNumber">Card Number:</label>
        <input type="text" id="cardNumber" name="cardNumber" pattern="[0-9]{16}" required><br>

        <label for="expiryDate">Expiry Date:</label>
        <input type="text" id="expiryDate" name="expiryDate" placeholder="MM/YY" required>
        
        <label for="cvc">CVC:</label>
        <input type="text" id="cvc" name="cvc" pattern="[0-9]{3}" required><br>

        <%
            // Check for form submission and validate payment details
            if (request.getMethod().equalsIgnoreCase("POST")) {
                String enteredCardName = request.getParameter("cardName");
                String enteredCardNumber = request.getParameter("cardNumber");
                String enteredExpiryDate = request.getParameter("expiryDate");
                String enteredCvc = request.getParameter("cvc");

                // Validate user data
                if (!enteredCardName.equalsIgnoreCase(user.get_first_name() + " " + user.get_last_name()) ||
                    !enteredCardNumber.equals(user.get_card_num()) ||
                    !enteredExpiryDate.equals(user.get_card_expiry_date()) ||
                    !enteredCvc.equals(String.valueOf(user.get_card_cvc()))) {
                    out.println("<p style='color: red;'>Incorrect payment details! Please try again.</p>");
                } else {
                    // Address variables for the order
                    String streetNum = user.get_address_street_num();
                    String street = user.get_address_street();
                    String city = user.get_address_city();
                    int postcode = user.get_address_postcode();

                    // If delivery is chosen and the address was updated, get the new values
                    if (deliveryMethod.equals("delivery")) {
                        streetNum = request.getParameter("streetNumber");
                        street = request.getParameter("streetAddress");
                        city = request.getParameter("city");
                        postcode = Integer.parseInt(request.getParameter("postcode"));
                    }

                    // Add new order to the database
                    db.create_order(user.get_id(), cart, totalPrice, deliveryMethod, streetNum, street, city, postcode);

                    // Redirect to submitOrder.jsp if successful
                    response.sendRedirect("submitOrder.jsp");
                }
            }
        %>

        <br>
        <button type="submit">Submit Order</button>
    </form>

    <!-- Navigation Buttons -->
    <div style="margin-top: 20px;">
        <a href="landing.jsp" class="button">Home Page</a>
        <a href="checkout.jsp" class="button">Review Order</a>
    </div>

    <%
            } // End of cart check
        } // End of user check
    %>

</body>
</html>


