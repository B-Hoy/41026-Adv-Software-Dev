<%
    String firstNameParam = request.getParameter("first_name");
    String lastNameParam = request.getParameter("last_name");

    if (firstNameParam != null && lastNameParam != null) {
        // Create and add cookies
        Cookie firstName = new Cookie("first_name", firstNameParam);
        Cookie lastName = new Cookie("last_name", lastNameParam);
        
        // Set the cookies to expire after 24 hours
        firstName.setMaxAge(24 * 60 * 60);
        lastName.setMaxAge(24 * 60 * 60);
        
        // Add cookies to the response
        response.addCookie(firstName);
        response.addCookie(lastName);
    }
%>


<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>Setting Cookies</title>
    </head>
<body>
<h1>Setting Cookies</h1>
    <form action="myProfile.jsp" method="GET">
    First Name: <input id="first_name" type="text" name="first_name" placeholder="John"> <br /><br/>
    Last Name: <input id="last_name" type="text" name="last_name" placeholder="Smith"> <br /><br/>
    Email: <input type="text" name="email" placeholder="Email@email.com"/><br/><br/> 
    Password: <input id="password" type="password" name="password" placeholder="********"> <br /><br/>
    Credit Card Number: <input id="card_num" type="text" name="card_num" placeholder="************1234"> <br /><br/>
    Card Expiry: <input id="card_exp" type="text" name="card_exp" placeholder="08/27"> <br /><br/>
    Phone Number: <input id="phone" type="text" name="phone" placeholder="041234578"> <br /><br/>
    
    <input type="submit" value="Submit" />
   </form>
 <ul>
  <b>First Name:</b><%=request.getParameter("first_name")%><br /><br/>
  <b>Last Name:</b><%=request.getParameter("last_name")%>
</body>
</html>