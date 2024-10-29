<%@ page import="uts.advsoft.Database" %>
<%@ page import="java.util.*" %>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Arrays"%>
<%@page import="uts.advsoft.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<% 
    User currentUser = (User)session.getAttribute("user");
    Database db = (Database)application.getAttribute("database");

    if (db == null) {
        throw new Exception("Database connection is not initialized.");
    }
    
    if (currentUser == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    currentUser.set_email(request.getParameter("email"));
    currentUser.set_first_name(request.getParameter("first_name"));
    currentUser.set_last_name(request.getParameter("last_name"));
    currentUser.set_password(request.getParameter("password"));
    currentUser.set_phone_num(request.getParameter("phone_num"));
    currentUser.set_card_num(request.getParameter("card_num"));
    currentUser.set_card_expiry_date(request.getParameter("card_expiry_date"));
    currentUser.set_card_cvc(Integer.parseInt(request.getParameter("card_cvc")));
    currentUser.set_address_street_num(request.getParameter("address_street_num"));
    currentUser.set_address_street(request.getParameter("address_street"));
    currentUser.set_address_city(request.getParameter("address_city"));
    currentUser.set_address_postcode(Integer.parseInt(request.getParameter("address_postcode")));

    db.update_user(currentUser);    

    response.sendRedirect("myProfile.jsp");
%>
