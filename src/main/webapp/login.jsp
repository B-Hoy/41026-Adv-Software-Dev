<%@ page import="uts.advsoft.Database" %>
<%@ page import="java.util.*" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Arrays"%>
<%@page import="uts.advsoft.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Database db = (Database)application.getAttribute("database");

    if (email != null && password != null) {
        %><p><%=email + " " + password%></p><%
        User user = db.get_user(email, password); // Use the existing method to check for user
        if (user != null) {
            session.setAttribute("user", user);
            response.sendRedirect("main.jsp"); // Redirect to the home page
        } else {
            out.println("Invalid email or password. Please try again."); // User not found
        }
    }
%>

<html>
    <head>
        <title>Login</title>
    </head>

    <body>
        <h1>Login</h1>
        <form method="post">
            Email: <input type="text" name="email" required><br>
            Password: <input type="password" name="password" required><br>
            <input type="submit" value="Login">
        </form>

    </body>
</html>