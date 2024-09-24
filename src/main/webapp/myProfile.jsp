<html>
    <head>
        <title>My Profile</title>
    </head>
    <body>

        <div class="topnav">
            <a href="main.jsp">Home</a>
            <a href="myProfile.jsp">My Profile</a>
            <a href="orderHistory.jsp">Order History</a>
            <a href="logout.jsp" style="float:right;">Logout</a>
        </div>

        
        <h1>Read Cookies</h1>

        <%
            // Initialize variables for first and last names
            String firstName = "";
            String lastName = "";
            String email = "";
            String cardNum = "";
            String cardExp = "";
            String phone = "";

            // Retrieve the cookies from the request
            Cookie[] cookies = request.getCookies();

            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("first_name".equals(cookie.getName())) {
                        firstName = cookie.getValue();
                    }
                    if ("last_name".equals(cookie.getName())) {
                        lastName = cookie.getValue();
                    }
                    if ("email".equals(cookie.getName())) {
                        email = cookie.getValue();
                    }
                    if ("card_num".equals(cookie.getName())) {
                        cardNum = cookie.getValue();
                    }
                    if ("card_exp".equals(cookie.getName())) {
                        cardExp = cookie.getValue();
                    }
                    if ("phone".equals(cookie.getName())) {
                        phone = cookie.getValue();
                    }
                }
            }
        %>

        <b>First Name:</b> <%= firstName %><br />
        <b>Last Name:</b> <%= lastName %><br />
        <b>Email:</b> <%= email %><br />
        <b>Card Number:</b> <%= cardNum %><br />
        <b>Card Expiry:</b> <%= cardExp %><br />
        <b>Phone Number:</b> <%= phone %><br />

    </body>
</html>
