<%@ page import="java.util.List"%>
<%@ page import="java.util.Arrays"%>
<%@page import="uts.advsoft.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uts.advsoft.Database"%>
<% Database db = (Database)application.getAttribute("database"); %> 


<html>
    <body>

        <div class="container">
            <h1>Register</h1>
    
    
            <form action="/devops-assignment/register_user.jsp" method="POST">          
                <input type="hidden" id="form_type" name="Register_form" value="insert">
    
                <div class="account-info">
                    <h2>Account Information</h2>
                    <p><strong>First Name:</strong></p>
                    <div class="container">
                        <p>Enter Name</p>
                        <input id="first_name"
                            type="text" name="first_name"
                            placeholder="John" required>
                    </div>
    
                    <div class="account-info">
                        <p><strong>Last Name:</strong></p>
                        <div class="container">
                            <p>Enter Name</p>
                            <input id="last_name"
                                type="text" name="last_name"
                                placeholder="Smith" required>
                        </div>
    
                    <p><strong>Email:</strong></p>
                    <div class="container">
                        <p>Enter Email</p>
                        <input id="email"
                            type="text" name="email"
                            placeholder="Email@email.com" required>
                    </div>

                    <p><strong>Phone Number:</strong></p>
                    <div class="container">
                        <p>Enter Phone Number</p>
                        <input id="phone_num"
                            type="text" name="phone_num"
                            placeholder=" 0412345678 " required>
                    </div>
                    <br>
    
                    <p><strong>Password:</strong></p>
                    <div class="container">
                        <p>Enter Password</p>
                        <input id="password"
                            type="password" name="password"
                            placeholder="********" required>
                    </div>
    
                    <p><strong>Credit Card Info:</strong></p>
                    <div class="container">
                        <p>Enter Credit Card Info</p>
                        <input id="card_num"
                            type="text" name="card_num"
                            placeholder="************1234" required >
                    </div>
    
                    <p><strong>Card Expiry:</strong></p>
                    <div class="container">
                        <p>Enter Card Expiry</p>
                        <input id="card_exp"
                            type="text" name="card_exp"
                            placeholder=" 0412345678 " required >
                    </div>

                    <p><strong>CVC:</strong></p>
                    <div class="container">
                        <input id="card_cvc" 
                        type="text" name ="card_cvc" 
                        placeholder=" 987 "required>
                    </div>
    
                    <p><strong>Street Number:</strong></p>
                    <div class="container">
                        <input id="address_street_num" 
                        type="text" name="address_street_num" 
                        placeholder="South St" required>
                    </div>

                    <p><strong>Street Name:</strong></p>
                    <div class="container">
                        <input id="address_street" 
                        type="text" name="address_street" 
                        placeholder="South St" required>
                    </div>

                    <p><strong>City:</strong></p>
                    <div class="container">
                        <input id="address_city" 
                        type="text" name="address_city" 
                        placeholder="Sydney" required>
                    </div>

                    <p><strong>Postcode:</strong></p>
                    <div class="container">
                        <input id="address_postcode" 
                        type="text" name="address_postcode" 
                        placeholder="2000" required>
                    </div>

                    
                    <br>
                    <input type="submit" value="Submit">
    
                </form>

                <script>
                    document.getElementById("form_type").addEventListener("submit", function() {
                        // Redirect to main.html
                        window.location.href = "/devops-assignment/register_user.jsp";
                    });
                </script>

            </div>
        </div>
        

    </body>

</html>