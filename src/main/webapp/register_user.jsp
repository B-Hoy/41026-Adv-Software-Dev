<%@ page import="uts.advsoft.Database" %>
<%@ page import="java.util.*" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Arrays"%>
<%@page import="uts.advsoft.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<% 
    Database db = (Database)application.getAttribute("database");
    
    String firstName = request.getParameter("first_name");
    String lastName = request.getParameter("last_name");
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String phoneNumber = request.getParameter("phone_num");
    String cardNum = request.getParameter("card_num");
    String cardExpiry = request.getParameter("card_exp");
    int cardCvv = Integer.parseInt(request.getParameter("card_cvc"));
    String addressStreetNum = request.getParameter("address_street_num");
    String addressStreet = request.getParameter("address_street");
    String addressCity = request.getParameter("address_city");
    int addressPostcode = Integer.parseInt(request.getParameter("address_postcode"));
    
    db.make_user(email, firstName, lastName, password, phoneNumber, cardNum, cardExpiry, cardCvv, addressStreetNum, addressStreet, addressCity, addressPostcode);
    
    User newUser = db.get_user(email, password);
    
    session.setAttribute("user", newUser);
    response.sendRedirect("myProfile.jsp"); // Redirect to the profile page after registration
%>