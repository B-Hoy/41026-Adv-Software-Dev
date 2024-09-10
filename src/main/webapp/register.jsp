<html>
    <body>

        <div class="container">
            <h1>Register</h1>
    
    
            <form action="/ASD/src/main/webapp/main.jsp" method="POST">          
                <input type="hidden" id="form_type" name="form_type" value="insert">
    
                <div class="account-info">
                    <h2>Account Information</h2>
                    <p><strong>First Name:</strong></p>
                    <div class="container">
                        <p>Enter Name</p>
                        <input id="first_name"
                            type="text" name="first_name"
                            placeholder="John">
                    </div>
    
                    <div class="account-info">
                        <p><strong>Last Name:</strong></p>
                        <div class="container">
                            <p>Enter Name</p>
                            <input id="last_name"
                                type="text" name="last_name"
                                placeholder="Smith">
                        </div>
    
                    <p><strong>Email:</strong></p>
                    <div class="container">
                        <p>Enter Email</p>
                        <input id="email"
                            type="text" name="email"
                            placeholder="Email@email.com">
                    </div>
    
                    <p><strong>Password:</strong></p>
                    <div class="container">
                        <p>Enter Password</p>
                        <input id="password"
                            type="password" name="password"
                            placeholder="********">
                    </div>
    
                    <p><strong>Credit Card Info:</strong></p>
                    <div class="container">
                        <p>Enter Credit Card Info</p>
                        <input id="card_num"
                            type="text" name="card_num"
                            placeholder="************1234">
                    </div>
    
                    <p><strong>Card Expiry:</strong></p>
                    <div class="container">
                        <p>Enter Card Expiry</p>
                        <input id="card_exp"
                            type="text" name="card_exp"
                            placeholder=" 0412345678 ">
                    </div>
    
                    <p><strong>Phone Number:</strong></p>
                    <div class="container">
                        <p>Enter Phone Number</p>
                        <input id="phone_num"
                            type="text" name="phone_num"
                            placeholder=" 0412345678 ">
                    </div>
                    <br>
                    <br>
                    <input type="submit" value="Submit">
    
                </form>

                <!--
                <script>
                    document.getElementById("form_type").addEventListener("submit", function() {
                        // Redirect to main.html
                        window.location.href = "/iotbay/web_pages/main.jsp";
                    });
                </script>
                -->

            </div>
        </div>
        
    
    
    </body>

</html>