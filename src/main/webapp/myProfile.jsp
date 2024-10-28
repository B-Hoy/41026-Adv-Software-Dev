<%@page import="uts.advsoft.Database"%>
<%@page import="uts.advsoft.Order"%>
<%@page import="uts.advsoft.User"%>
<%@page import="uts.advsoft.MenuItem"%>
<%@page import="uts.advsoft.MenuItemEntry"%>
<%@page import="uts.advsoft.Employee"%>
<%@page import="uts.advsoft.Cart"%>
<% Database db = (Database)application.getAttribute("database"); %> 

<%
    User currentUser = (User)session.getAttribute("user");
    if (currentUser == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>


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

        
        <h1>Account Information</h1>


        <p><strong>First Name:</strong> <%= currentUser.get_first_name() %></p>
        <p><strong>Last Name:</strong> <%= currentUser.get_last_name() %></p>
        <p><strong>Email:</strong> <%= currentUser.get_email() != null ? currentUser.get_email() : "N/A" %></p>
        <p><strong>Phone Number:</strong> <%= currentUser.get_phone_num() %></p>
        <p><strong>Card Number:</strong> **** **** **** <%= currentUser.get_card_num().substring(currentUser.get_card_num().length() > 4 ? currentUser.get_card_num().length() - 4 : 0) %></p>
        <p><strong>Card Expiry Date:</strong> <%= currentUser.get_card_expiry_date() %></p>
        <p><strong>CVC:</strong> <%= currentUser.get_card_cvc() %></p>
        <p><strong>Street:</strong> <%= currentUser.get_address_street() %> <%= currentUser.get_address_street() %></p>
        <p><strong>City:</strong> <%= currentUser.get_address_city() %></p>
        <p><strong>Postcode:</strong> <%= currentUser.get_address_postcode() %></p>



        <h2>Change Details</h2>

        <!-- Change user from showing to updating-->
        <form action="update_user.jsp" method="post"> <!-- update to updateHandler after testing-->
            <h2>Update Details</h2>
            <p><strong>Email:</strong> <input type="text" name="email" value="<%= currentUser.get_email() != null ? currentUser.get_email() : "" %>"></p>
            <p><strong>First Name:</strong> <input type= "text" name="first_name" value="<%= currentUser.get_first_name() %>"></p>
            <p><strong>Last Name:</strong> <input type="text" name="last_name" value="<%= currentUser.get_last_name() %>"></p>
            <p><strong>Password:</strong> <input type="text" name="password" value="<%= currentUser.get_password() %>"></p>
            <p><strong>Phone Number:</strong> <input type="text" name="phone_num" value="<%= currentUser.get_phone_num() %>"></p>
            <p><strong>Card Number:</strong> <input type="text" name="card_num" value="<%= currentUser.get_card_num() %>"></p>
            <p><strong>Card Expiry Date:</strong> <input type="text" name="card_expiry_date" value="<%= currentUser.get_card_expiry_date() %>"></p>
            <p><strong>CVC:</strong> <input type="text" name="card_cvc" value="<%= currentUser.get_card_cvc() %>"></p>
            <p><strong>Street:</strong> <input type="text" name="address_street" value="<%= currentUser.get_address_street() %>"></p>
            <p><strong>City:</strong> <input type="text" name="address_city" value="<%= currentUser.get_address_city() %>"></p>
            <p><strong>Postcode:</strong> <input type="text" name="address_postcode" value="<%= currentUser.get_address_postcode() %>"></p>
            
            <input type="submit" value="Update">
        </form>
        



    </body>
</html>
